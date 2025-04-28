package com.waw.majorproject2.rest;

import com.google.api.Http;
import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.models.CropImage;
import com.waw.majorproject2.models.FarmImage;
import com.waw.majorproject2.models.PlotImage;
import com.waw.majorproject2.repositories.CropImageRepository;
import com.waw.majorproject2.repositories.CropRepository;
import com.waw.majorproject2.repositories.FarmImageRepository;
import com.waw.majorproject2.repositories.PlotImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ImageRestController {
    @Autowired
    FarmImageRepository farmImageRepository;
    @Autowired
    PlotImageRepository plotImageRepository;
    @Autowired
    CropImageRepository cropImageRepository;


    //save images to database.
    @PostMapping("/api/images/farm/addImage")
    public ResponseEntity<FarmImage> addFarmImage(@RequestBody FarmImage farmImage){
        return ResponseEntity.ok(farmImageRepository.save(farmImage));
    }

    @PostMapping("/api/images/plot/addImage")
    public ResponseEntity<PlotImage> addPlotImage(@RequestBody PlotImage plotImage){
        return ResponseEntity.ok(plotImageRepository.save(plotImage));
    }

    @PostMapping("/api/images/crop/addImage")
    public ResponseEntity<CropImage> addCropImage(@RequestBody CropImage cropImage){
        return ResponseEntity.ok(cropImageRepository.save(cropImage));
    }

    //get images for database
    @GetMapping("/api/images/farm/{id}")
    public ResponseEntity<FarmImage> getFarmImage(@PathVariable("id") Long farmImageId){
        Optional<FarmImage> farmImageOptional = farmImageRepository.findById(farmImageId);
        return farmImageOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(null));
    }

    @GetMapping("/api/images/plot/{id}")
    public  ResponseEntity<PlotImage> getPlotImage(@PathVariable("id") Long plotImageId){
        Optional<PlotImage> plotImageOptional = plotImageRepository.findById(plotImageId);
        return plotImageOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(null));
    }

    @GetMapping("/api/images/crop/{id}")
    public  ResponseEntity<CropImage> getCropImage(@PathVariable("id") Long cropImageId){
        Optional<CropImage> cropImageOptional = cropImageRepository.findById(cropImageId);
        return cropImageOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(null));
    }


    //delete images
    @DeleteMapping("/api/images/farm/{id}")
    public ResponseEntity<Boolean> deleteFarmImage(@PathVariable("id") Long farmImageId){
        try
        {
            farmImageRepository.deleteById(farmImageId);
            return ResponseEntity.ok(true);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }

    }
    @DeleteMapping("/api/images/plot/{id}")
    public ResponseEntity<Boolean> deletePlotImage(@PathVariable("id") Long plotImageId){
        try
        {
            plotImageRepository.deleteById(plotImageId);
            return ResponseEntity.ok(true);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }

    }
    @DeleteMapping("/api/images/crop/{id}")
    public ResponseEntity<Boolean> deleteCropImage(@PathVariable("id") Long cropImageId){
        try
        {
            cropImageRepository.deleteById(cropImageId);
            return ResponseEntity.ok(true);
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(false);
        }

    }

    @GetMapping("/api/images/farms/{id}")
    public ResponseEntity<List<Long>> getFarmImageIds(@PathVariable("id") Long farmId){
        return ResponseEntity.ok(farmImageRepository.getFarmImageIds(farmId));
    }

    @GetMapping("/api/images/plots/{id}")
    public ResponseEntity<List<Long>> getPlotImageIds(@PathVariable("id") Long plotId){
        return ResponseEntity.ok(plotImageRepository.getPlotImageIds(plotId));
    }

    @GetMapping("/api/images/crops/{id}")
    public ResponseEntity<List<Long>> getCropImageIds(@PathVariable("id") Long cropId){
        return ResponseEntity.ok(cropImageRepository.getCropImageIds(cropId));
    }
}
