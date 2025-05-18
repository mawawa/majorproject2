package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plot {
    @Id
    @SequenceGenerator(name = "plot_generator", sequenceName = "plot_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plot_generator")
    private Long plotId;
    @ManyToMany(cascade = CascadeType.DETACH)
    private List<WawUser> owners;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Farm farm;
    private double width;
    private double height;

    private String name;


    public Plot() {
    }


    public Plot(Long plotId, List<WawUser> owners, Farm farm, double width, double height, List<Crop> cropsList, String name) {
        this.plotId = plotId;
        this.owners = owners;
        this.farm = farm;
        this.width = width;
        this.height = height;
        this.name = name;
    }




    public String getName() {
        return name;
    }

    public List<WawUser> getOwners() {
        return owners;
    }

    public void setOwners(List<WawUser> owners) {
        this.owners = owners;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
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


}
