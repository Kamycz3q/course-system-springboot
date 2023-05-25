package com.kamycz3q.coursesystemspringboot.guest.addresses.models;


public record AddressModel (
    String street,
    String buildingNo,
    String flatNo,
    String postalCode,
    String city,
    String country
){}
