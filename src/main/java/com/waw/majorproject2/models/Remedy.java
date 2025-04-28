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
    private String description;
    private String application;
    @OneToMany
    private List<Defect> plantDefectList;

    public Remedy() {
    }

    public Remedy(Long id, String name, String description, String application, List<Defect> plantDefectList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.application = application;
        this.plantDefectList = plantDefectList;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Defect> getPlantDefectList() {
        return plantDefectList;
    }

    public void setPlantDefectList(List<Defect> plantDefectList) {
        this.plantDefectList = plantDefectList;
    }
}
