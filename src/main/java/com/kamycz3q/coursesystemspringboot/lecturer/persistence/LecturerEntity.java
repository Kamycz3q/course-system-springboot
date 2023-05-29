package com.kamycz3q.coursesystemspringboot.lecturer.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LecturerEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    Long id;
    String displayName;
    Long customerId;
}
