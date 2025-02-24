package com.waw.majorproject2.models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class CropDefect {
    @Id
    @SequenceGenerator(name="plant_defect_generator", sequenceName = "plant_defect_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_defect_generator")
    private Long plantDefectId;
    private String name;
    private String description;
    private Date date;

    @OneToMany
    private List<Remedy> remediesList;

}
