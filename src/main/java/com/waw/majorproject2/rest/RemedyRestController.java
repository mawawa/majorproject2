package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Remedy;
import com.waw.majorproject2.repositories.RemedyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RemedyRestController {
    @Autowired
    RemedyRepository remedyRepository;
    @GetMapping("/api/remedies")
    public List<Remedy> getRemedies(){
        return remedyRepository.findAll();
    }

}
