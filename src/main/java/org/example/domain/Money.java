package org.example.domain;

public record Money(double unitPrice) {
    public Money{
        if(unitPrice < 0){
            throw new IllegalArgumentException("The unit price can not be negative");
        }
    }
}
