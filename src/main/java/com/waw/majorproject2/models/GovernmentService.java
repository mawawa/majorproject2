package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class GovernmentService {

    @Id
    @SequenceGenerator(name = "gov_service_generator", sequenceName = "gov_service_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gov_service_generator")
    private Long id;
    private String serviceName;
    private String serviceDescription;
    private String serviceStatus;
    private String contactPerson;
    private String dateAdded;
    @ManyToOne
    private WawUser officer;
    @OneToMany
    private List<Farm> registeredFarms = new ArrayList<>();
    private String serviceFee;


    public GovernmentService(Long id, String serviceName, String serviceDescription, String serviceStatus, String contactPerson, String dateAdded, WawUser officer, List<Farm> registeredFarms, String serviceFee) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceStatus = serviceStatus;
        this.contactPerson = contactPerson;
        this.dateAdded = dateAdded;
        this.officer = officer;
        this.registeredFarms = registeredFarms;
        this.serviceFee = serviceFee;
    }

    public GovernmentService() {
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public WawUser getOfficer() {
        return officer;
    }

    public void setOfficer(WawUser officer) {
        this.officer = officer;
    }

    public List<Farm> getRegisteredFarms() {
        return registeredFarms;
    }

    public void setRegisteredFarms(List<Farm> registeredFarms) {
        this.registeredFarms = registeredFarms;
    }
}
