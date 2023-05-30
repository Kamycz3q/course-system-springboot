package com.kamycz3q.coursesystemspringboot.course.enrollment.absence;


import com.kamycz3q.coursesystemspringboot.course.enrollment.Enrollment;
import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import jakarta.persistence.*;
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
    @ManyToOne
    private CourseEntity Course;
    @OneToOne
    private Enrollment enrollment;
}
