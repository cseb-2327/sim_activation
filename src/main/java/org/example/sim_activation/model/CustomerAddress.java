package org.example.sim_activation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
@Entity
@Table(name = "CustomerAddress")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressid;

    private String address;
    private String city;
    private Long pincode;
    private String state;

    // Default No-Arg Constructor (Required by JPA)
    public CustomerAddress(){}
    public CustomerAddress(String address, Long pincode, String state, String city) {
    }

    // Parameterized Constructor
    public CustomerAddress(String address,String city,Long pincode,String state) {


        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;

    }


    // --- Getters and Setters ---


    public Long getPincode() {
        return pincode;
    }
    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}