package com.waw.majorproject2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Farm {
    @Id
    @SequenceGenerator(name = "farm_generator", sequenceName = "farm_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_generator")
    private Long farmId;
    private String name;
    private double width;
    private double height;
    private String location;
    private String description;
    @OneToMany
    private List<Plot> plots;
    @ManyToMany
    private List<WawUser> owners;

    public Farm() {
    }




    public Farm(Long farmId, String name, double width, double height, String location, String description, List<Plot> plots, List<WawUser> owners) {
        this.farmId = farmId;
        this.name = name;
        this.width = width;
        this.height = height;
        this.location = location;
        this.description = description;
        this.plots = plots;
        this.owners = owners;
    }

    public Long getFarmId() {
        return farmId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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

    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }

    public List<WawUser> getOwners() {
        return owners;
    }

    public void setOwners(List<WawUser> owners) {
        this.owners = owners;
    }
}
