package com.kamycz3q.coursesystemspringboot.customer.api;


import com.kamycz3q.coursesystemspringboot.customer.logic.CustomerService;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.request.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.api.dto.CustomerDTO;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CustomerDTO>> listCustomers() {
        return customerService.listCustomers();
    }

    @PostMapping()
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomerFromData(createCustomerRequest.personalData(), createCustomerRequest.companyData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") String id){
        return customerService.getCustomer(Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String id){
        return customerService.deleteCustomer(Long.parseLong(id));
    }

}

