package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.services.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @PostMapping("/api/crops/multiple")
    public ResponseEntity<List<Crop>> newCrops(@RequestBody List<Crop> newCrops){
        System.out.println("saving multiple crops");
        int position = 1;
        for(Crop c : newCrops){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                java.util.Date date = dateFormat.parse(c.getDateString());
                c.setDatePlanted(new java.sql.Date(date.getTime()));
                c.setPosition(position);
                position++;
            }catch(ParseException e){
                e.printStackTrace();
            }

            cropService.saveUpdateCrop(c);
        }
        newCrops = new ArrayList<>();
        System.out.println("crops added successfully");
        return ResponseEntity.ok(newCrops);

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
                    c.setDateString(c.getDatePlanted().toString());
                    c.setDatePlanted(null);
            ownersCrops.add(c);
        }
        return ResponseEntity.ok(ownersCrops);
    }

}
