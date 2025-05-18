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
    private String nameOfOrganicRemedy;
    private String descriptionOfOrganicRemedy;
    private String hotToApply;

    public Remedy(Long id, String nameOfOrganicRemedy, String descriptionOfOrganicRemedy, String hotToApply) {
        this.id = id;
        this.nameOfOrganicRemedy = nameOfOrganicRemedy;
        this.descriptionOfOrganicRemedy = descriptionOfOrganicRemedy;
        this.hotToApply = hotToApply;
    }

    public Remedy() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfOrganicRemedy() {
        return nameOfOrganicRemedy;
    }

    public void setNameOfOrganicRemedy(String nameOfOrganicRemedy) {
        this.nameOfOrganicRemedy = nameOfOrganicRemedy;
    }

    public String getDescriptionOfOrganicRemedy() {
        return descriptionOfOrganicRemedy;
    }

    public void setDescriptionOfOrganicRemedy(String descriptionOfOrganicRemedy) {
        this.descriptionOfOrganicRemedy = descriptionOfOrganicRemedy;
    }

    public String getHotToApply() {
        return hotToApply;
    }

    public void setHotToApply(String hotToApply) {
        this.hotToApply = hotToApply;
    }
}
