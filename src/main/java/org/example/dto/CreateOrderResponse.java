package org.example.dto;

import org.example.domain.Address;
import org.example.domain.CustomerName;
import org.example.domain.EmailAddress;
import org.example.domain.PaymentMethod;

import java.util.List;

public record CreateOrderResponse(CustomerName customerName, EmailAddress emailAddress, Address address,
                                  List<OrderItemResponse> itemResponses, PaymentMethod paymentMethod) {
}
