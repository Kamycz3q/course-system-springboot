package com.kamycz3q.coursesystemspringboot.course.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByCourseId(Long courseId);
    Optional<Enrollment> findByCourseIdAndCustomerId(Long courseId, Long customerId);
}
