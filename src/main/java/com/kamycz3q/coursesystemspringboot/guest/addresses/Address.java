package com.kamycz3q.coursesystemspringboot.guest.addresses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    @Column(insertable=false, updatable=false)private String street;
    @Column(insertable=false, updatable=false)private String buildingNo;
    @Column(insertable=false, updatable=false)private String flatNo;
    @Column(insertable=false, updatable=false)private String postalCode;
    @Column(insertable=false, updatable=false)private String city;
    @Column(insertable=false, updatable=false)private String country;

}
