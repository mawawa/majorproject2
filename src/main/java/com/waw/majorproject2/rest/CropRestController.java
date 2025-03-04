package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.CropRepository;
import com.waw.majorproject2.services.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CropRestController {

    @Autowired
    CropService cropService;
    @GetMapping("/api/crops")
    public List<Crop> getCrops(){
        return cropService.getAllCrops();
    }

    @PostMapping("/api/crops")
    public Crop newCrop(@RequestBody Crop newCrop){
        return cropService.saveUpdateCrop(newCrop);
    }

    @GetMapping("/api/crops/get/{id}")
    public ResponseEntity<Crop> getCrop(@PathVariable Long id){
        return ResponseEntity.ok(cropService.findById(id));
    }

    @DeleteMapping("/api/crops/{id}")
    public void deleteCrop(@PathVariable Long id){
        cropService.deleteCropById(id);
    }

    @GetMapping("/api/crops/owner/{id}")
    public ResponseEntity<List<Crop>> getOwnerCrops(@PathVariable Long id){
        List<Crop> allCrops = cropService.getAllCrops();
        List<Crop> ownersCrops = new ArrayList<>();
        for(Crop c: allCrops){
            for(WawUser owner: c.getOwners()){
                if(owner.getId().equals(id)){
                    ownersCrops.add(c);
                    break;
                }

            }
        }
        return ResponseEntity.ok(ownersCrops);
    }
}
