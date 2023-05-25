package com.kamycz3q.coursesystemspringboot.guest.companyData;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;


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
    @Nullable private String nip;
    @Nullable private String regon;
}
