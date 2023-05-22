package com.kamycz3q.coursesystemspringboot.customer.companyData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CompanyData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private Long customerId;
    private String email, companyName;
    private String city, address, postCode;
    private String nip;
}
