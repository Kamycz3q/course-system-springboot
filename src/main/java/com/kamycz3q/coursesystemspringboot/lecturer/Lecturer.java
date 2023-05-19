package com.kamycz3q.coursesystemspringboot.lecturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    Long id;
    String displayName;
    Long customerId;
}
