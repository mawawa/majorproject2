package com.waw.majorproject2.models;

import jakarta.persistence.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Entity
public class CropImage implements Serializable {
    @SequenceGenerator(name = "waw_image_generator", sequenceName = "waw_image_generator", initialValue = 1, allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "waw_image_generator")
    @Id
    private Long imageId;

    @Lob
    private String imageData;

    @ManyToOne(cascade = CascadeType.DETACH)
    Crop crop;

    public CropImage() {
    }
    public  InputStream base64ToInputStream() {
        byte[] byteArray = Base64.decodeBase64(imageData);
        InputStream imageInputStream = new ByteArrayInputStream(byteArray);
        return imageInputStream;
    }



    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public CropImage(Long imageId, Long capturedItemId, String imageData) {
        this.imageId = imageId;
        this.imageData = imageData;
    }
    public CropImage(Long capturedItemId, String imageData) {
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
