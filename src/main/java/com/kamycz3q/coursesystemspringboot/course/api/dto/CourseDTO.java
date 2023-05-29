package com.kamycz3q.coursesystemspringboot.course.api.dto;

import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Validated
public record CourseDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        Float cost,
        @NotEmpty
        LecturerEntity lecturerEntity,
        @NotEmpty
        Timestamp startDate,
        @NotEmpty
        Timestamp endDate
) {

}
