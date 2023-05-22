package com.kamycz3q.coursesystemspringboot.customer;

import com.kamycz3q.coursesystemspringboot.customer.enums.AccountPermissions;
import com.kamycz3q.coursesystemspringboot.customer.models.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.models.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.customer.personalData.PersonalData;
import com.kamycz3q.coursesystemspringboot.customer.personalData.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonalDataRepository personalDataRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PersonalDataRepository personalDataRepository) {
        this.customerRepository = customerRepository;
        this.personalDataRepository = personalDataRepository;
    }

    public Customer createUserFromPersonalNumber(CreateCustomerRequest req) throws Exception {

        //tryb konta osobistego
//        else {
//            type = 1;
//            if (req.personalNumber().toString().length() != 11) {
//                isPersonalNumberValid = false;
//
//            }
//        }
//        i

        PersonalData personalData = personalDataRepository.findPersonalDataByPesel(req.personalNumber());
        if (personalData == null) {
            throw new Exception("PESEL dont exists in database!");
        }
        Customer customer = new Customer();
        customer.setLogin(req.login());
        customer.setPassword(req.password());
        customer.setPersonalDataId(personalData.getId());
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
