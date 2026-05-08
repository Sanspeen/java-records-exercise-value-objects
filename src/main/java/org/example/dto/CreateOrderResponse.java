package org.example.dto;

import org.example.domain.Address;
import org.example.domain.CustomerName;
import org.example.domain.EmailAddress;
import org.example.domain.PaymentMethod;

import java.util.List;
import java.util.UUID;

public record CreateOrderResponse(String orderId, CustomerName customerName, EmailAddress emailAddress, Address address,
                                  List<OrderItemResponse> itemResponses, PaymentMethod paymentMethod, double total) {
}
