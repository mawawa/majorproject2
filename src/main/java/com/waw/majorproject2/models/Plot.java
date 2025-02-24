package com.waw.majorproject2.models;


import com.waw.majorproject2.services.WawUsersService;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plot {
    @Id
    @SequenceGenerator(name = "plot_generator", sequenceName = "plot_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plot_generator")
    private Long plotId;
    @ManyToOne
    private WawUser owner;
    @ManyToOne
    private Farm farm;
    private double width;
    private double height;
    @OneToMany
    private List<Crop> cropsList;

    public Plot() {
    }

    public Plot(Long plotId, WawUser owner, Farm farm, double width, double height, List<Crop> cropsList) {
        this.plotId = plotId;
        this.owner = owner;
        this.farm = farm;
        this.width = width;
        this.height = height;
        this.cropsList = cropsList;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public WawUser getOwner() {
        return owner;
    }

    public void setOwner(WawUser owner) {
        this.owner = owner;
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

    public List<Crop> getCropsList() {
        return cropsList;
    }

    public void setCropsList(List<Crop> cropsList) {
        this.cropsList = cropsList;
    }
}
