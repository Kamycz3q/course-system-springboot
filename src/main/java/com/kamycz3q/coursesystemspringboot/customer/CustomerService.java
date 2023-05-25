package com.kamycz3q.coursesystemspringboot.customer;

import com.kamycz3q.coursesystemspringboot.customer.models.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.models.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.exception.ApiNotFoundException;
import com.kamycz3q.coursesystemspringboot.exception.ApiWrongRequestException;
import com.kamycz3q.coursesystemspringboot.guest.addresses.Address;
import com.kamycz3q.coursesystemspringboot.guest.addresses.ContactData;
import com.kamycz3q.coursesystemspringboot.guest.addresses.models.AddressModel;
import com.kamycz3q.coursesystemspringboot.guest.addresses.models.ContactDataModel;
import com.kamycz3q.coursesystemspringboot.guest.companyData.CompanyData;
import com.kamycz3q.coursesystemspringboot.guest.companyData.models.CompanyDataModel;
import com.kamycz3q.coursesystemspringboot.guest.personalData.PersonalData;
import com.kamycz3q.coursesystemspringboot.guest.personalData.models.PersonalDataModel;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final List<Integer> regonWeights9 = new ArrayList<>(List.of(8,9,2,3,4,5,6,7));
    private final List<Integer> regonWeights14 = new ArrayList<>(List.of(2,4,8,5,0,9,7,3,6,1,2,4,8));
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    private boolean validateCompanyDataNIP(@NonNull String nip) {
        //nip sprwadzanie
        int sum = 0;
        if (nip.length() >= 8 && nip.length() <= 11) {
            return true;
        }
        //sprawdzanie regon

        else if (nip.length() == 9) {
            for (int i = 0; i < 9;i++) {
                int number = Integer.parseInt(String.valueOf(String.valueOf(nip).charAt(i)));
                int weight = regonWeights9.get(i);
                sum += number * weight;
            }
            if (sum % 11 != 10) {
                return true;
            }
        } else if (nip.length() == 14) {
            for (int i = 0; i < 14;i++) {
                int number = Integer.parseInt(String.valueOf(String.valueOf(nip).charAt(i)));
                int weight = regonWeights9.get(i);
                sum += number * weight;
            }
            if (sum % 11 != 10) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPesel(String pesel) {
        if (pesel.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(pesel);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    public CustomerDTO customerToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getPersonalData(),
                customer.getCompanyData()
        );
    }

    public Customer createCustomerFromData(PersonalDataModel personalDataModel, CompanyDataModel companyDataModel) {
        if (personalDataModel == null || companyDataModel == null) {
            throw new ApiWrongRequestException("Provided wrong data");

        }
        Customer customer = new Customer();

        System.out.println(personalDataModel);
        System.out.println(companyDataModel);
        //personal data setup
        PersonalData personalData = new PersonalData();
        personalData.setName(personalDataModel.name());
        personalData.setSurname(personalDataModel.surname());
        personalData.setPesel(personalDataModel.pesel());
        Address address = new Address();
        address.setCity(personalDataModel.address().city());
        address.setStreet(personalDataModel.address().street());
        address.setBuildingNo(personalDataModel.address().buildingNo());
        address.setCountry(personalDataModel.address().country());
        address.setFlatNo(personalDataModel.address().flatNo());
        address.setPostalCode(personalDataModel.address().postalCode());
        personalData.setAddress(address);
        ContactData contactData = new ContactData();
        contactData.setEmail(personalDataModel.contactData().email());
        contactData.setPhoneNumber(personalDataModel.contactData().phoneNumber());
        personalData.setContactData(contactData);
        //personal data setup

        //company data setup
        CompanyData companyData = new CompanyData();
        companyData.setCompanyName(companyDataModel.companyName());
        companyData.setNip(companyDataModel.nip());
        companyData.setRegon(companyDataModel.regon());
        address = new Address();
        address.setCity(companyDataModel.address().city());
        address.setStreet(companyDataModel.address().street());
        address.setBuildingNo(companyDataModel.address().buildingNo());
        address.setCountry(companyDataModel.address().country());
        address.setFlatNo(companyDataModel.address().flatNo());
        address.setPostalCode(companyDataModel.address().postalCode());
        companyData.setAddress(address);
        contactData = new ContactData();
        contactData.setEmail(companyDataModel.contactData().email());
        contactData.setPhoneNumber(companyDataModel.contactData().phoneNumber());
        companyData.setContactData(contactData);
        //company data setup

        if (!checkPesel(personalData.getPesel())) {
            throw new ApiWrongRequestException("Provided wrong pesel");
        }
        if (companyData.getNip() == null && companyData.getRegon() == null) {
            throw new ApiWrongRequestException("NIP or REGON are empty");
        }
        if (companyData.getNip() != null && !validateCompanyDataNIP(companyData.getNip())) {
            throw new ApiWrongRequestException("NIP or REGON are not valid");
        } else if (companyData.getRegon() != null && !validateCompanyDataNIP(companyData.getRegon())){
            throw new ApiWrongRequestException("NIP or REGON are not valid");
        }
        customer.setPersonalData(personalData);
        customer.setCompanyData(companyData);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        customerRepository.delete(optionalCustomer.get());
    }

    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getPersonalData(),
                customer.getCompanyData()
        )).collect(Collectors.toList());
    }
    public Customer getCustomer(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        return optionalCustomer.get();
    }
}
