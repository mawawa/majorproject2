package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.CropImage;
import com.waw.majorproject2.models.CropResearchNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CropResearchNotesRepository extends JpaRepository<CropResearchNote, Long> {
    @Query("SELECT n FROM CropResearchNote n WHERE n.researcher.id = :userId ")
    List<CropResearchNote> findByUserId(Long userId);

    @Query("SELECT n FROM CropResearchNote n WHERE n.crop.id = :cropId")
    List<CropResearchNote> findByCropId(Long cropId);
}
