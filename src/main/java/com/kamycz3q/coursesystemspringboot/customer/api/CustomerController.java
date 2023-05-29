package com.kamycz3q.coursesystemspringboot.customer.api;


import com.kamycz3q.coursesystemspringboot.customer.logic.CustomerService;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.request.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.customer.persistence.CustomerEntity;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping()
    public CustomerDTO createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        CustomerEntity savedCustomer = customerService.createCustomerFromData(createCustomerRequest.personalData(), createCustomerRequest.companyData());
        return CustomerDTO.fromCustomer(savedCustomer);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") String id) throws Exception {
        CustomerEntity customerEntity = customerService.getCustomer(Long.parseLong(id));
        return CustomerDTO.fromCustomer(customerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) throws Exception {
        customerService.deleteCustomer(Long.parseLong(id));
    }

}

