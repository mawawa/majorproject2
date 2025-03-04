package com.waw.majorproject2.services;

import com.waw.majorproject2.models.NokthulaAI;
import com.waw.majorproject2.repositories.NokthulaAIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class NokhtulaAIService {
    @Autowired
    NokthulaAIRepository nokthulaAIRepository;
    public NokthulaAI getNokthulaAI(Long id){
        return nokthulaAIRepository.findById(id).get();
    }
}
