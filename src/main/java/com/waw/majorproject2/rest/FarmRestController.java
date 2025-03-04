package com.waw.majorproject2.rest;


import com.waw.majorproject2.models.Farm;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class FarmRestController {

    @Autowired
    FarmRepository farmRepository;
    @GetMapping("/api/farms")
    public ResponseEntity<List<Farm>> getFarms(){
        return ResponseEntity.ok(farmRepository.findAll());
    }

    @PostMapping("/api/farms")
    public ResponseEntity<Farm> saveFarm(@RequestBody Farm farm){
        return ResponseEntity.ok(farmRepository.save(farm));
    }

    @DeleteMapping("/api/farms/{id}")
    public void deleteFarm(@PathVariable Long id){
        farmRepository.deleteById(id);
    }

    @GetMapping("api/farms/get/{id}")
    public ResponseEntity<Farm> getFarm (@PathVariable Long id){
        return ResponseEntity.ok(farmRepository.findById(id).get());
    }

    @GetMapping("api/farms/owner/{id}")
    public ResponseEntity<List<Farm>> getOwnerFarms(@PathVariable Long id){
        List<Farm> allFarms = farmRepository.findAll();
        List<Farm> ownerFarms = new ArrayList();
        for(Farm f: allFarms){
            for(WawUser owner: f.getOwners()){
                if(Objects.equals(owner.getId(), id))
                    ownerFarms.add(f);
            }
        }
        return ResponseEntity.ok(ownerFarms);
    }
}
