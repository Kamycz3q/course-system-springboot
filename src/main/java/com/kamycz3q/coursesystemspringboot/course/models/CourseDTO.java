package com.kamycz3q.coursesystemspringboot.course.models;

import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;

import java.sql.Timestamp;

public record CourseDTO(
        String name,
        String description,
        Float cost,
        LecturerEntity lecturerEntity,
        Timestamp startDate,
        Timestamp endDate
) {

}
