package org.example.dto;

import org.example.domain.Money;
import org.example.domain.ProductId;
import org.example.domain.Quantity;

public record OrderItemResponse(ProductId productId, Quantity quantity, Money money) {
}
