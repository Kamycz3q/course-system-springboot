package com.kamycz3q.coursesystemspringboot.guest.addresses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private String street;
    private String buildingNo;
    private String flatNo;
    private String postalCode;
    private String city;
    private String country;

}
