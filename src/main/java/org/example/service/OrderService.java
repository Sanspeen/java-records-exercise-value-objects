package org.example.service;

import org.example.dto.CreateOrderRequest;
import org.example.domain.*;
import org.example.dto.CreateOrderResponse;
import org.example.dto.OrderItemResponse;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {

    public static final double TAXES_AMOUNT_VALUE = 0.19;

    public CreateOrderResponse createOrder(CreateOrderRequest request){

        // Transformar request in value objects
        CustomerName customerNameVO = new CustomerName(request.customerName());
        EmailAddress emailAddressVO = new EmailAddress(request.email());
        Address addressVO = new Address(request.street(), request.city(), request.country());
        List<OrderItemResponse> itemsVO = request.items().stream()
                .map(orderItemRequest -> new OrderItemResponse(
                        new ProductId(orderItemRequest.productId()),
                        new Quantity(orderItemRequest.quantity()),
                        new Money(orderItemRequest.unitPrice())
                )).toList();
        PaymentMethod paymentMethodsVO = new PaymentMethod(request.paymentMethod());

        // Calcular el subtotal sumando quantity * unitPrice
        double subtotal = itemsVO.stream().map(OrderService::calculateSubtotal)
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getSum();

        // Calcular impuesto del 19%
        double tax = subtotal * TAXES_AMOUNT_VALUE;

        // Calcular el total final
        double total = subtotal + tax;

        // Generar el orderId
        String orderId = UUID.randomUUID().toString();

        // Devolver un CreateOrderResponse

        return new CreateOrderResponse(orderId, customerNameVO, emailAddressVO, addressVO, itemsVO, paymentMethodsVO, total);
    }

    private static double calculateSubtotal(OrderItemResponse items) {
        return items.money().unitPrice() * items.quantity().quantity();
    }


}
