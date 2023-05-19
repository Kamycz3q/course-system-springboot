package com.kamycz3q.coursesystemspringboot.customer;

import com.kamycz3q.coursesystemspringboot.customer.enums.AccountPermissions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private Long personalDataId;
    private String login, password;
    private List<AccountPermissions> accountPermissions;
}
