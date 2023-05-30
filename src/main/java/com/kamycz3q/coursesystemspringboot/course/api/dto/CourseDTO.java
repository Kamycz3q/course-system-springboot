package com.kamycz3q.coursesystemspringboot.course.api.dto;

import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;
import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;

@Validated
public record CourseDTO(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        @NotEmpty
        Integer membersLimit,
        @NotEmpty
        Float cost,
        @NotEmpty
        LecturerEntity lecturerEntity,
        @NotEmpty
        Timestamp startDate,
        @NotEmpty
        Timestamp endDate
) {
        public static @NotNull CourseDTO fromCourse(CourseEntity course) {
                return new CourseDTO(
                        course.getName(),
                        course.getDescription(),
                        course.getMembersLimit(),
                        course.getCost(),
                        course.getLecturerEntity(),
                        course.getStartDate(),
                        course.getEndDate()
                );
        }
}
