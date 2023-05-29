package com.kamycz3q.coursesystemspringboot.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
