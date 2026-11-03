package org.example.sim_activation.repository;

import org.example.sim_activation.model.SimDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SimDetailsRepository extends JpaRepository<SimDetails, Long> {
    // Custom method to find the SIM record by the actual phone/SIM number
    Optional<SimDetails> findByServiceNumber(Long ServiceNumber);
}