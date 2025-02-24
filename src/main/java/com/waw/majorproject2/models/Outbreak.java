package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;


@Entity
public class Outbreak {
    @Id
    @SequenceGenerator(name="outbreak_generator", sequenceName ="outbreak_generator", initialValue =  0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbreak_generator")
    private Long OutbreakId;
    private String location;
    private String name;
    private String description;
    private String firstName;
    private String lastName;
    private String contactDetails;
    private Date date;

    @OneToMany
    private List<CropDefect> plantDefectList;
    
    public Outbreak() {
    }

    public Outbreak(Long outbreakId, String location, String name, String description, String firstName, String lastName, String contactDetails, Date date) {
        OutbreakId = outbreakId;
        this.location = location;
        this.name = name;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
        this.date = date;
    }

    public Long getOutbreakId() {
        return OutbreakId;
    }

    public void setOutbreakId(Long outbreakId) {
        OutbreakId = outbreakId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
