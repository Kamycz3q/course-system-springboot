package com.kamycz3q.coursesystemspringboot.course.enrollment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enrollment {
    @Id
    @GeneratedValue
    private Long id;
    private Long CustomerId;
    private Long courseId;
}
