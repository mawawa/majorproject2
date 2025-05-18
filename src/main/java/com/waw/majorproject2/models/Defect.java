package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Defect {
    @Id
    @SequenceGenerator(name="plant_defect_generator", sequenceName = "plant_defect_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_defect_generator")
    private Long plantDefectId;
    private String name;
    private String description;
    private String cause;
    @OneToMany(cascade = CascadeType.DETACH)
    List<Remedy> listOfOrganicRemedies;


    public Defect() {
    }

    public Defect(Long plantDefectId, String name, String description, String cause, List<Remedy> listOfOrganicRemedies, Crop crop) {
        this.plantDefectId = plantDefectId;
        this.name = name;
        this.description = description;
        this.cause = cause;
        this.listOfOrganicRemedies = listOfOrganicRemedies;

    }

    public Defect(Long plantDefectId, String name, String description, String cause, List<Remedy> listOfOrganicRemedies) {
        this.plantDefectId = plantDefectId;
        this.name = name;
        this.description = description;
        this.cause = cause;
        this.listOfOrganicRemedies = listOfOrganicRemedies;
    }

    public List<Remedy> getListOfOrganicRemedies() {
        return listOfOrganicRemedies;
    }

    public void setListOfOrganicRemedies(List<Remedy> listOfOrganicRemedies) {
        this.listOfOrganicRemedies = listOfOrganicRemedies;
    }

    public Long getPlantDefectId() {
        return plantDefectId;
    }

    public void setPlantDefectId(Long plantDefectId) {
        this.plantDefectId = plantDefectId;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }


}
