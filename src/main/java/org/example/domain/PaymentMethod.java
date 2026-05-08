package org.example.domain;

import java.util.Objects;

public record PaymentMethod(PaymentMethods paymentMethod) {
    public PaymentMethod {
        Objects.requireNonNull(paymentMethod);
    }
}
