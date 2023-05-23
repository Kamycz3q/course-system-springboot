package com.kamycz3q.coursesystemspringboot.guest.personalData;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PersonalData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name, surname;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_data_id")
    private ContactData contactData;
    private String pesel;
    private Long companyId;
}
