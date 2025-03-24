package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class WawUser{
    @Id
    @SequenceGenerator(name="user_generator", sequenceName = "user_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String role;
    protected String password;
    protected String contactNumber;
    protected String emailAddress;
    protected boolean isActive;

    public WawUser() {
    }



    public WawUser(Long id, String firstName, String lastName, String role, String password, String contactNumber, String emailAddress, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
