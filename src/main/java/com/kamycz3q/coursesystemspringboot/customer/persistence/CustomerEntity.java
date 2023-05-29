package com.kamycz3q.coursesystemspringboot.customer.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
