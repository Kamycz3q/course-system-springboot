package com.kamycz3q.coursesystemspringboot.customer.logic;

import com.kamycz3q.coursesystemspringboot.customer.api.dto.*;
import com.kamycz3q.coursesystemspringboot.customer.persistence.*;
import com.kamycz3q.coursesystemspringboot.exception.ApiNotFoundException;
import com.kamycz3q.coursesystemspringboot.exception.ApiWrongRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;
    public CustomerEntity createCustomerFromData(PersonalDataDTO personalDataDTO, CompanyDataDTO companyDataDTO) {
        if (companyDataDTO.nip() == null && companyDataDTO.regon() == null) {
            throw new ApiWrongRequestException("NIP or REGON empty");
        }

        log.info("PersonalDataDTO: {}", personalDataDTO);

        CustomerEntity customerEntity = customerEntityMapper.toEntity(personalDataDTO, companyDataDTO);
        return customerRepository.save(customerEntity);
    }

    public void deleteCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        customerRepository.delete(optionalCustomer.get());
    }

    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerDTO::fromCustomer)
                .collect(toList());
    }

    public CustomerEntity getCustomer(Long customerId) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new ApiNotFoundException("Customer not found");
        }
        return optionalCustomer.get();
    }


}
