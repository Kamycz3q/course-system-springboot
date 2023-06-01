package com.kamycz3q.coursesystemspringboot.course.certificate.persistance;

import com.kamycz3q.coursesystemspringboot.course.persistence.CourseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class CertificateEntity {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private CourseEntity course;
    private String certificateName;
    private Date date;
    private String image;
}
