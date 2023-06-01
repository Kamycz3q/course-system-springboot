package com.kamycz3q.coursesystemspringboot.customer.persistence;

import com.kamycz3q.coursesystemspringboot.course.certificate.persistance.CertificateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class CustomerEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Embedded
    private PersonalData personalData;
    @Embedded
    private CompanyData companyData;
    @ManyToOne
    private List<CertificateEntity> certificates;
}
