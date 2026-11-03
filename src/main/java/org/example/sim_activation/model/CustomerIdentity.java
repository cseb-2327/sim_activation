package org.example.sim_activation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
@Entity
@Table(name = "CustomerIdentity")
public class CustomerIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueidnumber;

    private String dateOfbirth;
    private String name;
    private  String emailaddress;
    private String state;
    // Default No-Arg Constructor (Required by JPA)
    public CustomerIdentity() {
    }

    // Parameterized Constructor
    public CustomerIdentity(String dateOfbirth,String name,String emailaddress,String state) {

        this.dateOfbirth =dateOfbirth;
        this.emailaddress = emailaddress;
        this.name = name;
        this.state = state;

    }

    // --- Getters and Setters ---


    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Long getUniqueidnumber() {
        return uniqueidnumber;
    }

    public void setUniqueidnumber(Long uniqueidnumber) {
        this.uniqueidnumber = uniqueidnumber;
    }

    public String getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }
}