package org.example.sim_activation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String dob;
    private String address;
    private Long pincode;
    private Long idNumber;
    private Long simNumber;
    private Long serviceNumber;



    // Default No-Arg Constructor (Required by JPA)
    public Customer() {
    }

    // Parameterized Constructor
    public Customer(String name, String email, String dob, String address, Long pincode, Long idNumber, Long simNumber, Long serviceNumber) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.pincode = pincode;
        this.idNumber = idNumber;
        this.simNumber = simNumber;
        this.serviceNumber = serviceNumber;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Long getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(Long simNumber) {
        this.simNumber = simNumber;
    }

    public Long getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Long serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

}