package com.kamycz3q.coursesystemspringboot.guest.companyData.models;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import com.kamycz3q.coursesystemspringboot.guest.addresses.models.AddressModel;
import com.kamycz3q.coursesystemspringboot.guest.addresses.models.ContactDataModel;

public record CompanyDataModel(
        String companyName,
        AddressModel address,
        ContactDataModel contactData,
        String nip,
        String regon
){

}
