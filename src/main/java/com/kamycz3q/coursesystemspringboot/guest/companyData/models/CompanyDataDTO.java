package com.kamycz3q.coursesystemspringboot.guest.companyData.models;

import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;

public record CompanyDataDTO (
        Long id,
        Long personalDataId,
        String companyName,
        Address address,
        ContactData contactData,
        String nip,
        String regon
){

}
