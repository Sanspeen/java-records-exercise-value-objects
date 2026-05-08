package org.example.domain;

public record Quantity(int quantity) {
    public Quantity{
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity should be greater than 0");
        }
    }
}
