package com.waw.majorproject2.repositories;


import com.waw.majorproject2.models.NokthulaAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NokthulaAIRepository extends JpaRepository<NokthulaAI, Long> {
}

