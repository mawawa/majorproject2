package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.Outbreak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OutbreaksRepository extends JpaRepository<Outbreak, Long> {
}
