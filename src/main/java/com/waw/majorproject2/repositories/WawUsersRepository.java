package com.waw.majorproject2.repositories;

import com.waw.majorproject2.models.WawUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WawUsersRepository extends JpaRepository<WawUser, Long> {
}
