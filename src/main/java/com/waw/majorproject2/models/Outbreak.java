package com.waw.majorproject2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Outbreak {
    @Id
    @SequenceGenerator(name="outbreak_generator", sequenceName ="outbreak_generator", initialValue =  0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbreak_generator")
    private Long OutbreakId;
    private String location;
    private String description;
    private String firstName;
    private String lastName;
    private String contactDetails;


}
