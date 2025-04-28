package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.models.Plot;
import com.waw.majorproject2.repositories.PlotRepository;
import com.waw.majorproject2.services.CropService;
import com.waw.majorproject2.services.WawUsersService;
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
    WawUsersService wawUsersService;
    @Autowired
    PlotRepository plotRepository;

    @Autowired
    CropService cropService;
    @GetMapping("/api/crops")
    public List<Crop> getCrops(){
        return cropService.getAllCrops();
    }

    @GetMapping("/api/crop/plot/{id}")
    public ResponseEntity<List<Crop>> getPlotCrops(@PathVariable("id") Long plotId){
        List<Crop> plotCrops = new ArrayList<>();
        for(Crop c: getCrops()){
            if(c.getPlot().getPlotId() == plotId){
                plotCrops.add(c);
            }
        }
        return ResponseEntity.ok(plotCrops);
    }
    @PostMapping("/api/crops/{id}")
    public Crop newCrop(@RequestBody Crop newCrop){
        newCrop.setOwners(List.of(wawUsersService.getLoggedInUser()));
        return cropService.saveUpdateCrop(newCrop);

    }



    @PostMapping("/api/crops/multiple")
    public ResponseEntity<List<Crop>> newCrops(@RequestBody List<Crop> newCrops){
        System.out.println("saving multiple crops");
        int position = 1;
        for(Crop c : newCrops){
            System.out.println(c.getDateString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                java.util.Date date = dateFormat.parse(c.getDateString());
                c.setDatePlanted(new java.sql.Date(date.getTime()));
                c.setPosition(position);
                System.out.println(c.getDatePlanted().toString());
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
        for(Crop c: allCrops){
            c.setDateString(c.getDatePlanted().toString());
            c.setDatePlanted(null);
        }
        return ResponseEntity.ok(allCrops);
    }

}
