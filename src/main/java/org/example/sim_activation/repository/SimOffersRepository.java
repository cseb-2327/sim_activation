package org.example.sim_activation.repository;

import org.example.sim_activation.model.SimOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SimOffersRepository extends JpaRepository<SimOffers, Long> {
    // Custom method to find all offers linked to the internal simid
    List<SimOffers> findBySimid(Long simid);
}