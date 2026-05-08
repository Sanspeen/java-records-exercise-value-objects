package org.example.domain;

import java.util.Objects;

public record Address(String street, String city, String country) {

    public Address{
        Objects.requireNonNull(street);
        Objects.requireNonNull(city);
        Objects.requireNonNull(country);
    }

    public String fullAddress() {
        return street + ", " + city + ", " + country;
    }
}
