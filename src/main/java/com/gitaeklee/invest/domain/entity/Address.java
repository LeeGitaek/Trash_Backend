package com.gitaeklee.invest.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {
    private String city;
    private String state;

    private String street;
    private String zipcode;

    public Address(String city, String state, String street, String zipcode) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.zipcode = zipcode;
    }
}
