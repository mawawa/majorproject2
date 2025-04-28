package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class FarmImage implements Serializable {
    @SequenceGenerator(name = "waw_image_generator", sequenceName = "waw_image_generator", initialValue = 1, allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "waw_image_generator")
    @Id
    private Long imageId;

    @Lob
    private String imageData;

    @ManyToOne
    Farm farm;

    public FarmImage() {
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public FarmImage(Long imageId, Long capturedItemId, String imageData) {
        this.imageId = imageId;
        this.imageData = imageData;
    }
    public FarmImage(Long capturedItemId, String imageData) {
        this.imageData = imageData;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
