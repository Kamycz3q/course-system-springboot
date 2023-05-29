 package com.kamycz3q.coursesystemspringboot.course.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    List<CourseEntity> findAllByLecturer_Id(Long lecturerId);
}
