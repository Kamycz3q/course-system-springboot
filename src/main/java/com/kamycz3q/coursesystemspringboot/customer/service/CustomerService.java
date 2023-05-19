package com.kamycz3q.coursesystemspringboot.customer.service;

import com.kamycz3q.coursesystemspringboot.customer.Customer;
import com.kamycz3q.coursesystemspringboot.customer.CustomerRepository;
import com.kamycz3q.coursesystemspringboot.customer.enums.AccountPermissions;
import com.kamycz3q.coursesystemspringboot.customer.records.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.records.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalData;
import com.kamycz3q.coursesystemspringboot.personalData.personalDataObject.PersonalDataCompany;
import com.kamycz3q.coursesystemspringboot.personalData.repo.PersonalDataCompanyRepository;
import com.kamycz3q.coursesystemspringboot.personalData.repo.PersonalDataRepository;
import com.kamycz3q.coursesystemspringboot.personalData.service.PersonalDataCompanyService;
import com.kamycz3q.coursesystemspringboot.personalData.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonalDataCompanyService personalDataCompanyService;
    private final PersonalDataService personalDataService;
    private final PersonalDataRepository personalDataRepository;
    private final PersonalDataCompanyRepository personalDataCompanyRepository;
    private final List<Integer> nipWeights = List.of(6, 5, 7, 2, 3, 4, 5, 6, 7);

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PersonalDataCompanyService personalDataCompanyService, PersonalDataService personalDataService, PersonalDataRepository personalDataRepository, PersonalDataCompanyRepository personalDataCompanyRepository) {
        this.customerRepository = customerRepository;
        this.personalDataCompanyService = personalDataCompanyService;
        this.personalDataService = personalDataService;
        this.personalDataRepository = personalDataRepository;
        this.personalDataCompanyRepository = personalDataCompanyRepository;
    }

    private boolean checkIsNIP(Long personalNumber) {
        //        Pomnożyć każdą z pierwszych dziewięciu cyfr odpowiednio przez wagi: 6, 5, 7, 2, 3, 4, 5, 6, 7,
        //                Zsumować wyniki mnożenia,
        //        Obliczyć resztę z dzielenia przez 11 (operacja modulo 11).
        int sum = 0;
        for (int i = 0; i < 9;i++) {
            int number = Integer.parseInt(String.valueOf(String.valueOf(personalNumber).charAt(i)));
            int weight = nipWeights.get(i);
            sum += number * weight;
        }
        sum += Integer.parseInt(String.valueOf(String.valueOf(personalNumber).charAt(0))) + 1;
        return sum % 11 != 10;
    }
    public Customer createUserFromPersonalNumber(CreateCustomerRequest req) throws Exception {
        boolean isPersonalNumberValid = true;
        int type = 0;
        //tryb konta firmowego
        if (!checkIsNIP(req.personalNumber())) {
            isPersonalNumberValid = false;
        }
        //tryb konta osobistego
        else {
            type = 1;
            if (req.personalNumber().toString().length() != 11) {
                isPersonalNumberValid = false;

            }
        }
        if (!isPersonalNumberValid) {
            throw new Exception("Invalid personal number!");
        }


        if (type == 0) {
            PersonalData personalData = personalDataRepository.findPersonalDataByPesel(Integer.parseInt(String.valueOf(req.personalNumber())));
            if (personalData == null) {
                throw new Exception("PESEL dont exists in database!");
            }

        } else {
            PersonalDataCompany personalData = personalDataCompanyRepository.findPersonalDataCompanyByNip(req.personalNumber());
            if (personalData == null) {
                throw new Exception("NIP dont exists in database!");
            }
        }
        Customer customer = new Customer();
        customer.setLogin(req.login());
        customer.setPassword(req.password());
        customer.setPersonalDataId(req.personalNumber());
        return customerRepository.save(customer);

    }

    public void updateCustomerPassword(Long customerId, String oldPassword, String newPassword) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("Not valid user");
        }
        Customer customer = optionalCustomer.get();
        if (!customer.getPassword().equals(oldPassword)) {
            throw new Exception("Wrong old password");
        }
        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("Not valid user");
        }
        customerRepository.delete(optionalCustomer.get());
    }

    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream().map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getLogin()
        )).collect(Collectors.toList());
    }
    public Customer getCustomer(Long customerId) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("Not valid user");
        }
        return optionalCustomer.get();
    }


    public void performActionOnCustomer(Long customerId, String action) throws Exception {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new Exception("Not valid user");
        }
        Customer customer = optionalCustomer.get();

        switch(action) {
            case "add-lecturer": {
                List<AccountPermissions> accountPermissions = new ArrayList<>();
                if (customer.getAccountPermissions() != null) {
                    accountPermissions = customer.getAccountPermissions();
                }
                accountPermissions.add(AccountPermissions.LECTURER);
                customer.setAccountPermissions(accountPermissions);
            }
            ///wiecej w przyszlosci
        }
        System.out.println(customer.toString());
        customerRepository.save(customer);
    }
}
