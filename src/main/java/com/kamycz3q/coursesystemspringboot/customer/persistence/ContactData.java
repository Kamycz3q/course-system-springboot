package com.kamycz3q.coursesystemspringboot.customer.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ContactData {
    @Column(insertable = false, updatable = false)
    private String phoneNumber;

    @Column(insertable = false, updatable = false)
    private String email;

}
