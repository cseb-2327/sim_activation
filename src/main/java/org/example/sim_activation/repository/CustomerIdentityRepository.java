package org.example.sim_activation.repository;

import org.example.sim_activation.model.CustomerIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIdentityRepository extends JpaRepository<CustomerIdentity, Long> {
}