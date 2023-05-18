package com.kamycz3q.coursesystemspringboot.personalData.personalDataObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class PersonalDataCompany {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name, surname, email, companyName;
    private String city, address, postCode;
    private Long nip;
}
