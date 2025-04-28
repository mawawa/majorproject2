package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.FarmImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FarmImageRepository extends JpaRepository<FarmImage, Long> {
    @Query("SELECT i.id FROM FarmImage i WHERE i.farm.farmId = :farmId")
    List<Long> getFarmImageIds(Long farmId);
}
