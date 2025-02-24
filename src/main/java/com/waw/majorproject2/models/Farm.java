package com.waw.majorproject2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Farm {
    @Id
    @SequenceGenerator(name = "farm_generator", sequenceName = "farm_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farm_generator")
    private Long farmId;
    private double width;
    private double height;
    @OneToMany
    private List<Plot> plots;
    @ManyToMany
    private List<WawUser> owners;





}
