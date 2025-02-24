package com.waw.majorproject2.repositories;


import com.waw.majorproject2.models.CropDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDefectRepository extends JpaRepository<CropDefect, Long> {

}
