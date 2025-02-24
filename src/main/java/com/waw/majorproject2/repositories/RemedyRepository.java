package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.Remedy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedyRepository extends JpaRepository<Remedy, Long> {
}
