package com.kamycz3q.coursesystemspringboot.customer;


import com.kamycz3q.coursesystemspringboot.customer.models.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.models.CustomerDTO;
import lombok.NonNull;
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
    public CustomerDTO createCustomer(@NonNull @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.customerToDTO(customerService.createCustomerFromData(createCustomerRequest.personalData(), createCustomerRequest.companyData()));
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") String id) throws Exception {
        return customerService.getCustomer(Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) throws Exception {
        customerService.deleteCustomer(Long.parseLong(id));
    }

}

