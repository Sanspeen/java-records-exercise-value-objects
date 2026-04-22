package org.example.dto;

public record OrderItemRequest(String productId, int quantity, int unitPrice) {
}
