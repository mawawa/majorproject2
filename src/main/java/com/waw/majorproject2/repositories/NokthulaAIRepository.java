package com.waw.majorproject2.repositories;


import com.waw.majorproject2.models.NokthulaAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public interface NokthulaAIRepository extends JpaRepository<NokthulaAI, Long> {



}

