package com.kamycz3q.coursesystemspringboot.guest.personalData.models;

import com.kamycz3q.coursesystemspringboot.guest.addresses.models.AddressModel;
import com.kamycz3q.coursesystemspringboot.guest.addresses.models.ContactDataModel;


public record PersonalDataModel (
        String name, String surname,
        AddressModel address,
        ContactDataModel contactData,
        String pesel
){

}
