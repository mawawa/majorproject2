package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.TextMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextMessageRepository extends JpaRepository<TextMessage, Long> {
}
