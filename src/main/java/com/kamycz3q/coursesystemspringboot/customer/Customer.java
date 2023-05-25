package com.kamycz3q.coursesystemspringboot.customer;

import com.kamycz3q.coursesystemspringboot.customer.enums.AccountPermissions;
import com.kamycz3q.coursesystemspringboot.guest.companyData.CompanyData;
import com.kamycz3q.coursesystemspringboot.guest.personalData.PersonalData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
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
