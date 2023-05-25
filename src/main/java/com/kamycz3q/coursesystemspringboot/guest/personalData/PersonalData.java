package com.kamycz3q.coursesystemspringboot.guest.personalData;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PersonalData {
    private String name, surname;
    @Embedded
    private Address address;
    @Embedded
    private ContactData contactData;
    private String pesel;
}
