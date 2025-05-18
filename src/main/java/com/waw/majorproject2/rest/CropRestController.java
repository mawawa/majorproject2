package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.models.CropResearchNote;
import com.waw.majorproject2.models.Plot;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.CropRepository;
import com.waw.majorproject2.repositories.CropResearchNotesRepository;
import com.waw.majorproject2.repositories.PlotRepository;
import com.waw.majorproject2.services.CropService;
import com.waw.majorproject2.services.WawUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@RestController
public class CropRestController {
    @Autowired
    WawUsersService wawUsersService;
    @Autowired
    PlotRepository plotRepository;

    @Autowired
    CropService cropService;
    @Autowired
    CropResearchNotesRepository cropResearchNotesRepository;
    @Autowired
    CropRepository cropRepository;

    //Crop notes
    @PostMapping("/api/notes/")
    public ResponseEntity<CropResearchNote> addNote(@RequestBody CropResearchNote cropResearchNote){
        cropResearchNote.setDate(Instant.now().toString());
        //System.out.println(cropResearchNote.getCrop().getCropName());
        return ResponseEntity.ok(cropResearchNotesRepository.save(cropResearchNote));
    }

    @GetMapping("/api/notes/")
    public ResponseEntity<List<CropResearchNote>> getAllNotes(){
        return ResponseEntity.ok(cropResearchNotesRepository.findAll());
    }
    @GetMapping("/api/notes/owner/{userId}")
    public ResponseEntity<List<CropResearchNote>> getResearcherNotes(@PathVariable Long userId){
       return ResponseEntity.ok( cropResearchNotesRepository.findByUserId(userId));
    }

    @GetMapping("/api/notes/crop/{cropId}")
    public ResponseEntity<List<CropResearchNote>> getCropNotes(@PathVariable Long cropId){
        return ResponseEntity.ok(cropResearchNotesRepository.findByCropId(cropId));
    }

    //CROPS  api
    @GetMapping("/api/crops/")
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
    public ResponseEntity<Boolean> deleteCrop(@PathVariable Long id){
        try {
            cropService.deleteCropById(id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }

    }

    @GetMapping("/api/crops/owner/{id}")
    public ResponseEntity<List<Crop>> getOwnerCrops(@PathVariable Long id){
        List<Crop> allCrops = cropService.getAllCrops();
        List<Crop> ownerCrops = new ArrayList<>();
        for(Crop c: allCrops){
            for(WawUser o: c.getOwners()){
                if(o.getId() == id){
                    ownerCrops.add(c);
                }
            }
        }

        return ResponseEntity.ok(ownerCrops);
    }

}
