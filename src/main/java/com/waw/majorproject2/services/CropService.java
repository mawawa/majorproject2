package com.waw.majorproject2.services;


import com.waw.majorproject2.models.Crop;
import com.waw.majorproject2.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {


    @Autowired
    CropRepository cropRepository;
    public List<Crop> getAllCrops(){
        return  cropRepository.findAll();
    }

    public Crop saveUpdateCrop(Crop crop){
        return cropRepository.save(crop);
    }
    public Crop deleteCrop(Crop crop){
        cropRepository.delete(crop);
        return crop;
    }
    public Long deleteCropById(Long cropId){
        cropRepository.deleteById(cropId);
        return cropId;
    }

    public Crop findById(Long id) {
        return cropRepository.findById(id).get();

    }


    public Crop getCrop(Long id) {
       return cropRepository.findById(id).get();
    }


}
