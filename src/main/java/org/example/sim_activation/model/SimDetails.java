package org.example.sim_activation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "sim_Details")
public class SimDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Simid;

    private Long simNumber;
    private Long serviceNumber;
    private String status; //  "ACTIVE", "DEACTIVATED"




    // Default No-Arg Constructor (Required by JPA)
    public SimDetails() {
    }

    // Parameterized Constructor
    public SimDetails(Long simNumber, Long serviceNumber, String status) {
        this.simNumber = simNumber;
        this.serviceNumber = serviceNumber;
        this.status = status;

    }

    // --- Getters and Setters ---

    public Long getSimId() {
        return Simid;
    }

    public void setSimId(Long id) {
        this.Simid = Simid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}