package com.kamycz3q.coursesystemspringboot.customer.api.dto;

//W pakiecie DTO nie powinna przeciekać warstwa persystencji
//powinno miec wlasny model dla personalData i companyData
import com.kamycz3q.coursesystemspringboot.customer.persistence.CompanyData;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;
import com.kamycz3q.coursesystemspringboot.customer.persistence.PersonalData;

public record CustomerDTO(
        Long id,
        PersonalData personalData,
        CompanyData companyData
) {
    public static CustomerDTO fromCustomer(CustomerEntity customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getPersonalData(),
                customer.getCompanyData()
        );
    }
}
