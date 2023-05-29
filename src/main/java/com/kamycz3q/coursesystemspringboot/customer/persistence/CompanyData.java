package com.kamycz3q.coursesystemspringboot.customer.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CompanyData {
    private String companyName;

    @Embedded
    @Column(name = "company_address")
    private Address address;

    @Embedded
    @Column(name = "company_contact_data")
    private ContactData contactData;

    private String nip;

    private String regon;

}
