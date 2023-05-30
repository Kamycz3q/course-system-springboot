package com.kamycz3q.coursesystemspringboot.course.enrollment.absence.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Validated
public record CreateAbsenceRequest(
        @NotEmpty Long customerId,
        @NotEmpty Timestamp startTimestamp,
        @NotEmpty Timestamp endTimestamp,
        @NotEmpty Long courseId
) {

}
