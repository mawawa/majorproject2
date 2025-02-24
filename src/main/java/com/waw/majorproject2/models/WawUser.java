package com.waw.majorproject2.models;

import jakarta.persistence.*;

@Entity
public class WawUser{
    @Id
    @SequenceGenerator(name="user_generator", sequenceName = "user_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    protected Long Id;
    protected String firstName;
    protected String lastName;
    protected String Role;
    protected String password;
    protected String contactNumber;
    protected String emailAddress;
    protected boolean isActive;

    public WawUser() {
    }

    public WawUser(Long id, String firstName, String lastName, String role, String password, String contactNumber, String emailAddress, boolean isActive) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        Role = role;
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
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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
