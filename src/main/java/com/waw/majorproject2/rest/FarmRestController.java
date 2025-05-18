package com.waw.majorproject2.rest;


import com.waw.majorproject2.models.Farm;
import com.waw.majorproject2.models.FarmOfficerNote;
import com.waw.majorproject2.models.GovernmentService;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.FarmOfficerNoteRepository;
import com.waw.majorproject2.repositories.FarmRepository;
import com.waw.majorproject2.repositories.GovernmentServiceRepository;
import com.waw.majorproject2.repositories.WawUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class FarmRestController {


    @Autowired
    GovernmentServiceRepository governmentServiceRepository;
    @Autowired
    FarmOfficerNoteRepository farmOfficerNoteRepository;
    @Autowired
    FarmRepository farmRepository;
    @Autowired
    WawUsersRepository wawUsersRepository;
    @GetMapping("/api/farms")
    public ResponseEntity<List<Farm>> getFarms(){
        return ResponseEntity.ok(farmRepository.findAll());
    }

    @GetMapping("/api/farms/officer/get_gov_services")
    public ResponseEntity<List<GovernmentService>> getGovServices(){
      return ResponseEntity.ok(governmentServiceRepository.findAll());
    }

    @PostMapping("/api/farms/officer/add_gov_service")
    public ResponseEntity<GovernmentService> addGovService(@RequestBody GovernmentService service){
        return ResponseEntity.ok(governmentServiceRepository.save(service));
    }

    @PostMapping("/api/farms/officer/add_farm_note")
    ResponseEntity<FarmOfficerNote> addFarmNote(@RequestBody FarmOfficerNote officerNote){
        return ResponseEntity.ok(farmOfficerNoteRepository.save(officerNote));
    }


    @PostMapping("/api/farms/{id}")
    public ResponseEntity<Farm> saveFarm(@RequestBody Farm farm, @PathVariable Long id){
        farm.setOwners(new ArrayList<>());
        farm.getOwners().add(wawUsersRepository.findById(id).get());
        System.out.println(farm.getOwners().size()+ "here bro!!");
        return ResponseEntity.ok(farmRepository.save(farm));
    }

    @DeleteMapping("/api/farms/{id}")
    public ResponseEntity<Farm> deleteFarm(@PathVariable Long id){
        Farm farm = farmRepository.findById(id).get();
        farmRepository.deleteById(id);
        return ResponseEntity.ok(farm);
    }

    @GetMapping("api/farms/get/{id}")
    public ResponseEntity<Farm> getFarm (@PathVariable Long id){
        return ResponseEntity.ok(farmRepository.findById(id).get());
    }

    @GetMapping("api/farms/owner/{id}")
    public ResponseEntity<List<Farm>> getOwnerFarms(@PathVariable Long id){
        List<Farm> allFarms = farmRepository.findAll();
        List<Farm> ownerFarms = new ArrayList<>();
        for(Farm f: allFarms){
            for(WawUser owner: f.getOwners()){
                if(Objects.equals(owner.getId(), id))
                    ownerFarms.add(f);
            }
        }
        System.out.println(ownerFarms.size());
        return ResponseEntity.ok(ownerFarms);
    }
}
