package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.PlotImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlotImageRepository extends JpaRepository<PlotImage, Long> {
    @Query("SELECT i.id FROM PlotImage i WHERE i.plot.plotId = :plotId")
    List<Long> getPlotImageIds(Long plotId);
}
