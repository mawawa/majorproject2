package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Crop {
    @Id
    @SequenceGenerator(name = "crop_generator", sequenceName = "crop_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_generator")
    private Long id;
    private String location;
    private String description;
    private String cropName;
    @ManyToOne
    private Plot plot;
    @ManyToMany
    private List<Remedy> remediesUsed;
    @ManyToMany
    private List<CropDefect> cropDefects;
    private String condition;

    public Crop() {
    }

    public Crop(Long id, String location, String description, String cropName, List<Remedy> remediesUsed, List<CropDefect> cropDefects, String condition) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.cropName = cropName;
        this.remediesUsed = remediesUsed;
        this.cropDefects = cropDefects;
        this.condition = condition;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<CropDefect> getCropDefects() {
        return cropDefects;
    }

    public void setCropDefects(List<CropDefect> cropDefects) {
        this.cropDefects = cropDefects;
    }
}
