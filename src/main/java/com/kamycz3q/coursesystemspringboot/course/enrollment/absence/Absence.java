package com.kamycz3q.coursesystemspringboot.course.enrollment.absence;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Absence {
    @Id
    @GeneratedValue
    private Long id;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private Long courseId;
    private Long enrollmentId;
}
