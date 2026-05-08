package org.example.dto;

import org.example.domain.PaymentMethods;

import java.util.List;

public record CreateOrderRequest(String customerName, String email, String street, String city, String country,
                                 List<OrderItemRequest> items, PaymentMethods paymentMethod) {
}
