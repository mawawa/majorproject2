package com.waw.majorproject2.services;

import com.waw.majorproject2.models.Farm;
import com.waw.majorproject2.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {
    @Autowired
    FarmRepository farmRepository;

    public List<Farm> getFarmRepository() {
        return farmRepository.findAll();
    }
}
