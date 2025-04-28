package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.CropImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CropImageRepository extends JpaRepository<CropImage, Long> {
    @Query("SELECT i.id FROM CropImage i WHERE i.crop.id = :cropId")
    List<Long> getCropImageIds(Long cropId);

}
