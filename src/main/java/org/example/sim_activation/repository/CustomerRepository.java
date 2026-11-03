package org.example.sim_activation.repository;

import org.example.sim_activation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Validates if the user entered their exact registration details correctly
    Optional<Customer> findByNameAndIdNumberAndSimNumberAndServiceNumber(
            String name,
            Long idNumber,
            Long simNumber,
            Long serviceNumber
    );
}