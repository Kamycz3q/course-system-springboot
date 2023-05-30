package com.kamycz3q.coursesystemspringboot.course.enrollment.absence.dto;

import com.kamycz3q.coursesystemspringboot.course.api.dto.CourseDTO;
import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.enrollment.absence.Absence;


import java.sql.Timestamp;

public record AbsenceDTO (
        Enrollment enrollment,
        CourseDTO courseDTO,
        Timestamp startTimestamp,
        Timestamp endTimestamp
){
    public static AbsenceDTO fromAbsence(Absence absence) {
        return new AbsenceDTO(
                absence.getEnrollment(),
                CourseDTO.fromCourse(absence.getCourse()),
                absence.getStartTimestamp(),
                absence.getEndTimestamp()
        );
    }
}
