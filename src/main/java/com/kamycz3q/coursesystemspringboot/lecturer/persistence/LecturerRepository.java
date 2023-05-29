package com.kamycz3q.coursesystemspringboot.lecturer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<LecturerEntity, Long> {
    public LecturerEntity getLecturerByCustomerId(Long customerID);
}
