package org.example.sim_activation.controller;

import org.example.sim_activation.model.*;
import org.example.sim_activation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/activation")
public class SimActivationController {

    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository addressRepository;
    private final CustomerIdentityRepository identityRepository;
    private final SimDetailsRepository simDetailsRepository;
    private final SimOffersRepository simOffersRepository;

    @Autowired
    public SimActivationController(CustomerRepository customerRepository,
                                   CustomerAddressRepository addressRepository,
                                   CustomerIdentityRepository identityRepository,
                                   SimDetailsRepository simDetailsRepository,
                                   SimOffersRepository simOffersRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.identityRepository = identityRepository;
        this.simDetailsRepository = simDetailsRepository;
        this.simOffersRepository = simOffersRepository;
    }

    // ==========================================
    // STEP 1: CUSTOMER REGISTRATION
    // ==========================================

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "UserRegistrationPage";
    }

    @PostMapping("/register")
    public String registerCustomer(@RequestParam("name") String name,
                                   @RequestParam("email_ID") String email_ID,
                                   @RequestParam("DOB") String dob,
                                   @RequestParam("Address") String address,
                                   @RequestParam("State") String state,
                                   @RequestParam("City") String city,
                                   @RequestParam("Pincode") Long pincode,
                                   @RequestParam("ID_number") Long id_number,
                                   @RequestParam("SIM_number") Long sim_number,
                                   @RequestParam("Service_number") Long service_number,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (name == null || name.trim().isEmpty() || email_ID == null || email_ID.trim().isEmpty()) {
                throw new IllegalArgumentException("Name and Email ID are required.");
            }

            // 1. Save Core Customer
            Customer customer = new Customer(name, email_ID, dob, address, pincode, id_number, sim_number, service_number);
            customerRepository.saveAndFlush(customer);

            // 2. Save Customer Address
            CustomerAddress customerAddress = new CustomerAddress(address, city, pincode, state);
            addressRepository.saveAndFlush(customerAddress);

            // 3. Save Customer Identity for KYC
            CustomerIdentity identity = new CustomerIdentity(dob, name, email_ID, state);
            identityRepository.saveAndFlush(identity);

            redirectAttributes.addFlashAttribute("successMessage", "Customer registered successfully! Proceed to allocate SIM.");
            return "redirect:/activation/allocate-sim?simNumber=" + sim_number + "&serviceNumber=" + service_number;

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error during registration: " + e.getMessage());
            return "redirect:/activation/register?error";
        }
    }

    // ==========================================
    // STEP 2: ADMIN SIM ALLOCATION
    // ==========================================

    @GetMapping("/allocate-sim")
    public String showSimAllocationPage(@RequestParam(value = "simNumber", required = false) Long simNumber,
                                        @RequestParam(value = "serviceNumber", required = false) Long serviceNumber) {
        return "SimAllocationPage";
    }

    @PostMapping("/allocate-sim")
    public String allocateSim(@RequestParam("simNumber") Long simNumber,
                              @RequestParam("serviceNumber") Long serviceNumber,
                              RedirectAttributes redirectAttributes) {
        try {
            // Save SIM Details with an initial status of INACTIVE so the customer can activate it later
            SimDetails simDetails = new SimDetails(simNumber, serviceNumber, "INACTIVE");
            SimDetails savedSim = simDetailsRepository.saveAndFlush(simDetails);

            redirectAttributes.addFlashAttribute("successMessage", "SIM Allocated (Inactive)! Please assign an offer.");
            return "redirect:/activation/assign-offer?simId=" + savedSim.getSimId();

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to allocate SIM: " + e.getMessage());
            return "redirect:/activation/allocate-sim?error";
        }
    }

    // ==========================================
    // STEP 3: ADMIN OFFER SELECTION
    // ==========================================

    @GetMapping("/assign-offer")
    public String showOfferPage(@RequestParam(value = "simId", required = false) Long simId) {
        return "OfferSelectionPage";
    }

    @PostMapping("/assign-offer")
    public String assignOffer(@RequestParam("simId") Long simId,
                              @RequestParam("offerName") String offerName,
                              @RequestParam("callQty") Long callQty,
                              @RequestParam("dataQty") Long dataQty,
                              @RequestParam("cost") Long cost,
                              @RequestParam("duration") Long duration,
                              RedirectAttributes redirectAttributes) {
        try {
            // Save the chosen offer to the SimOffers table
            SimOffers offer = new SimOffers(callQty, dataQty, cost, duration, simId, offerName);
            simOffersRepository.saveAndFlush(offer);

            redirectAttributes.addFlashAttribute("successMessage", "Offer applied successfully! Setup complete.");
            return "redirect:/activation/success";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to assign offer: " + e.getMessage());
            return "redirect:/activation/assign-offer?simId=" + simId + "&error";
        }
    }

    @GetMapping("/success")
    public String activationComplete() {
        return "ActivationSuccessPage";
    }

    // ==========================================
    // STEP 4: CUSTOMER VIEW OFFERS
    // ==========================================

    @GetMapping("/view-offers")
    public String showViewOffersPage() {
        return "ViewOffersPage";
    }

    @PostMapping("/view-offers")
    public String getOffersBySimNumber(@RequestParam("ServiceNumber") Long ServiceNumber, Model model) {
        // 1. Find SIM Details by the user's SIM Number
        Optional<SimDetails> simDetailsOpt = simDetailsRepository.findByServiceNumber(ServiceNumber);

        if (simDetailsOpt.isEmpty()) {
            model.addAttribute("errorMessage", "No SIM found with number: " + ServiceNumber);
            return "ViewOffersPage";
        }

        // 2. Extract the internal simId and fetch associated offers
        Long internalSimId = simDetailsOpt.get().getSimId();
        List<SimOffers> offers = simOffersRepository.findBySimid(internalSimId);

        if (offers.isEmpty()) {
            model.addAttribute("errorMessage", "No offers currently active for SIM number: " + ServiceNumber);
        } else {
            model.addAttribute("offers", offers);
            model.addAttribute("simNumber", ServiceNumber);
        }

        return "ViewOffersPage";
    }

    // ==========================================
    // STEP 5: CUSTOMER SELF-ACTIVATION PORTAL
    // ==========================================

    @GetMapping("/customer-activate")
    public String showCustomerActivationPage() {
        return "CustomerActivatePage";
    }

    @PostMapping("/customer-activate")
    public String verifyAndActivateSim(@RequestParam("name") String name,
                                       @RequestParam("idNumber") Long idNumber,
                                       @RequestParam("simNumber") Long simNumber,
                                       @RequestParam("serviceNumber") Long serviceNumber,
                                       Model model) {

        // 1. Verify if these 4 exact details match a record in the Customer table
        Optional<Customer> validCustomer = customerRepository
                .findByNameAndIdNumberAndSimNumberAndServiceNumber(name, idNumber, simNumber, serviceNumber);

        if (validCustomer.isEmpty()) {
            model.addAttribute("errorMessage", "Activation Failed: Invalid Details. Please check your Name, ID, SIM, or Service Number.");
            return "CustomerActivatePage";
        }

        // 2. If valid, find the actual SIM record in the SimDetails table
        Optional<SimDetails> simDetailsOpt = simDetailsRepository.findByServiceNumber(serviceNumber);

        if (simDetailsOpt.isEmpty()) {
            model.addAttribute("errorMessage", "Activation Failed: SIM is registered but not yet allocated by admin.");
            return "CustomerActivatePage";
        }

        SimDetails simToActivate = simDetailsOpt.get();

        // 3. Check if already active
        if ("ACTIVE".equalsIgnoreCase(simToActivate.getStatus())) {
            model.addAttribute("successMessage", "Your SIM is already ACTIVE!");
            return "CustomerActivatePage";
        }

        // 4. Update status to ACTIVE and save
        simToActivate.setStatus("ACTIVE");
        simDetailsRepository.save(simToActivate);

        model.addAttribute("successMessage", "Verification Successful! Your SIM is now ACTIVATED.");
        return "CustomerActivatePage";
    }
}