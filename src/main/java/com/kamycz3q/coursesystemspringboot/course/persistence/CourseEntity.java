package com.kamycz3q.coursesystemspringboot.course.persistence;

import com.kamycz3q.coursesystemspringboot.lecturer.persistence.LecturerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class CourseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Float cost;
    private Integer membersLimit;
    @ManyToOne(cascade = CascadeType.ALL)
    private LecturerEntity lecturerEntity;
    private Timestamp startDate;
    private Timestamp endDate;
}
