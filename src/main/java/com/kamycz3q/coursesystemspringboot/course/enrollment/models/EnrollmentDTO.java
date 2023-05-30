package com.kamycz3q.coursesystemspringboot.course.enrollment.models;

import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;

public record EnrollmentDTO(
        CustomerEntity customerEntityData,
        Long courseId
) {

}
