package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Remedy  {

    @Id
    @SequenceGenerator(name="remedy_generator", sequenceName = "remedy_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "remedy_generator")
    private Long id;
    private String name;
    private Date dateRecorded;
    private String description;
    private String provider;
    @OneToMany
    private List<Defect> plantDefectList;

    public Remedy() {
    }

    public Remedy(Long id, String name, Date dateRecorded, String description, String provider, List<Defect> plantDefectList) {
        this.id = id;
        this.name = name;
        this.dateRecorded = dateRecorded;
        this.description = description;
        this.provider = provider;
        this.plantDefectList = plantDefectList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<Defect> getPlantDefectList() {
        return plantDefectList;
    }

    public void setPlantDefectList(List<Defect> plantDefectList) {
        this.plantDefectList = plantDefectList;
    }
}
