package com.kamycz3q.coursesystemspringboot.customer;


import com.kamycz3q.coursesystemspringboot.customer.models.CreateCustomerRequest;
import com.kamycz3q.coursesystemspringboot.customer.models.CustomerDTO;
import com.kamycz3q.coursesystemspringboot.customer.models.UpdatePasswordRequest;
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

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") String id) throws Exception {
        return customerService.getCustomer(Long.parseLong(id));
    }

    @PostMapping()
    public Customer createCustomerFromPersonalNumber(@RequestBody CreateCustomerRequest createCustomerRequest) throws Exception {
        return customerService.createUserFromPersonalNumber(createCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) throws Exception {
        customerService.deleteCustomer(Long.parseLong(id));
    }

    @PatchMapping("/{id}")
    public void updatePassword(@PathVariable("id") String userId, @RequestBody UpdatePasswordRequest updatePasswordRequest) throws Exception {
        customerService.updateCustomerPassword(Long.valueOf(userId), updatePasswordRequest.oldPassword(), updatePasswordRequest.newPassword());
    }

    @PatchMapping("/{id}/{action}")
    public void performAction(@PathVariable("id") String id, @PathVariable("action") String action) throws Exception {
        customerService.performActionOnCustomer(Long.valueOf(id), action);
    }

}

