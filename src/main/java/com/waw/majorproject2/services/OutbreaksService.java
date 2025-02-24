package com.waw.majorproject2.services;

import com.waw.majorproject2.models.Outbreak;
import com.waw.majorproject2.repositories.OutbreaksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutbreaksService {
    @Autowired
    OutbreaksRepository outbreaksRepository;

    public List<Outbreak> getOutbreaks(){
        return outbreaksRepository.findAll();
    }

    public Outbreak reportOutbreak(Outbreak outbreak){
        return outbreaksRepository.save(outbreak);
    }

    public Outbreak updateOutbreak(Outbreak outbreak){
        return outbreaksRepository.save(outbreak);
    }

    public Outbreak deleteOutbreak(Outbreak outbreak){
        outbreaksRepository.delete(outbreak);
        return outbreak;
    }

    public Long deleteOutbreak(String outbreakId){
        outbreaksRepository.deleteById(Long.parseLong(outbreakId));
        return Long.parseLong(outbreakId);
    }

}
