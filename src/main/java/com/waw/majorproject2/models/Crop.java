package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Crop {
    @Id
    @SequenceGenerator(name = "crop_generator", sequenceName = "crop_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_generator")
    private Long id;
    private int position;
    private String description;
    private String cropName;
    @ManyToOne
    private Plot plot;
    @ManyToMany
    private List<Remedy> remediesUsed;
    @ManyToMany
    private List<Defect> cropDefects;
    private String condition;
    private Date datePlanted;
    @Transient
    private String dateString;


    @ManyToMany
    private List<WawUser> owners;



    public Crop() {
    }

    public Crop(Long id, int position, String description, String cropName, Plot plot, List<Remedy> remediesUsed, List<Defect> cropDefects, String condition, Date datePlanted, String dateString, List<WawUser> owners) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.cropName = cropName;
        this.plot = plot;
        this.remediesUsed = remediesUsed;
        this.cropDefects = cropDefects;
        this.condition = condition;
        this.datePlanted = datePlanted;
        this.dateString = dateString;
        this.owners = owners;

    }

    public List<WawUser> getOwners() {
        return owners;
    }


    public void setOwners(List<WawUser> owners) {
        this.owners = owners;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Date getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(Date datePlanted) {
        this.datePlanted = datePlanted;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public List<Remedy> getRemediesUsed() {
        return remediesUsed;
    }

    public void setRemediesUsed(List<Remedy> remediesUsed) {
        this.remediesUsed = remediesUsed;
    }

    public List<Defect> getCropDefects() {
        return cropDefects;
    }

    public void setCropDefects(List<Defect> cropDefects) {
        this.cropDefects = cropDefects;
    }


}
