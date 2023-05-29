package com.kamycz3q.coursesystemspringboot.customer.persistence;

import com.kamycz3q.coursesystemspringboot.customer.api.dto.AddressDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.CompanyDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.ContactDataDTO;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.PersonalDataDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper {

    public CustomerEntity toEntity(PersonalDataDTO personalDataDTO, CompanyDataDTO companyDataDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPersonalData(getPersonalData(personalDataDTO));
        customerEntity.setCompanyData(getCompanyData(companyDataDTO));
        return customerEntity;
    }

    @NotNull
    private static CompanyData getCompanyData(CompanyDataDTO companyDataDTO) {
        CompanyData companyData = new CompanyData();
        companyData.setCompanyName(companyDataDTO.companyName());
        companyData.setNip(companyDataDTO.nip());
        companyData.setRegon(companyDataDTO.regon());
        companyData.setAddress(getAddress(companyDataDTO.address()));
        companyData.setContactData(getContactData(companyDataDTO.contactData()));
        return companyData;
    }

    @NotNull
    private static Address getAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCity(addressDTO.city());
        address.setStreet(addressDTO.street());
        address.setBuildingNo(addressDTO.buildingNo());
        address.setCountry(addressDTO.country());
        address.setFlatNo(addressDTO.flatNo());
        address.setPostalCode(addressDTO.postalCode());
        return address;
    }

    @NotNull
    private static ContactData getContactData(ContactDataDTO contactDataDTO) {
        ContactData contactData = new ContactData();
        contactData.setEmail(contactDataDTO.email());
        contactData.setPhoneNumber(contactDataDTO.phoneNumber());
        return contactData;
    }

    private static PersonalData getPersonalData(PersonalDataDTO personalDataDTO) {
        PersonalData personalData = new PersonalData();
        personalData.setName(personalDataDTO.name());
        personalData.setSurname(personalDataDTO.surname());
        personalData.setPesel(personalDataDTO.pesel());
        personalData.setAddress(getAddress(personalDataDTO.address()));
        personalData.setContactData(getContactData(personalDataDTO.contactData()));
        return personalData;
    }
}
