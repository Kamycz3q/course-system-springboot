package com.kamycz3q.coursesystemspringboot.customer.companyData.models;

public record CompanyDataDTO (
        Long id,
        Long customerId,
        String email, String companyName,
        String city, String address, String postCode,
        String nip
){

}
