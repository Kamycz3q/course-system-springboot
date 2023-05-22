package com.kamycz3q.coursesystemspringboot.customer.personalData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class PersonalData {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name, surname, email;
    private Date birthDate;
    private String city, address, postCode;
    private String pesel;
    private Long companyId;
}
