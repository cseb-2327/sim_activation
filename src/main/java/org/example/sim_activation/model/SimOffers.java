package org.example.sim_activation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import org.apache.juli.logging.Log;

@Entity
@Table(name = "SimOffers")
public class SimOffers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Offerid;

    private Long callQty;
    private Long Dataqty;
    private Long cost;
    private Long duration;
    private Long simid;
    private String offerName;

    public SimOffers() {
    }

    // Parameterized Constructor
    public SimOffers(Long callQty, Long Dataqty, Long cost, Long duration, Long simid, String offerName) {
        this.callQty = callQty;
        this.Dataqty = Dataqty;
        this.cost = cost;
        this.duration = duration;
        this.offerName = offerName;
        this.simid = simid;

    }

    // --- Getters and Setters ---

    public Long getSimId() {
        return simid;
    }

    public void setSimId(Long id) {
        this.simid = simid;
    }

    public Long getCallQty() {
        return callQty;
    }

    public void setCallQty(Long CallQty) {
        this.callQty = callQty;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getDataqty() {
        return Dataqty;
    }

    public void setDataqty(Long Dataqty) {
        this.Dataqty = Dataqty;
    }

    public Long getDuration() {
        return duration;
    }

    public Long getOfferid() {
        return Offerid;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setOfferid(Long offerid) {
        Offerid = offerid;
    }

    public Long getSimid() {
        return simid;
    }

    public void setSimid(Long simid) {
        this.simid = simid;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }


}