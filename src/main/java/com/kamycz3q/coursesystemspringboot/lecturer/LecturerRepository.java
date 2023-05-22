package com.kamycz3q.coursesystemspringboot.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    public Lecturer getLecturerByCustomerId(Long customerID);
}
