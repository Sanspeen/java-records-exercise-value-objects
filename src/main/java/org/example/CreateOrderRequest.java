package org.example;

import org.example.dto.OrderItemRequest;

import java.util.List;

public record CreateOrderRequest(String customerName, String email, String street, String city, String country,
                                 List<OrderItemRequest> items, String paymentMethod) {
}
