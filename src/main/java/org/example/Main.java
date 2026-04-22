package org.example;

import org.example.dto.OrderItemRequest;
import org.example.service.OrderService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        OrderService orderService = new OrderService();

        List<OrderItemRequest> items = List.of(new OrderItemRequest("id-1", 2, 1200), new OrderItemRequest("id-2", 1, 2000));
        CreateOrderRequest orderServiceRequest = new CreateOrderRequest(
                "Santiago Franco",
                "santiago@gmail.com",
                "123 Main street",
                "New york",
                "Colombia",
                items,
                "Credit card"
        );
        System.out.println();
        orderService.createOrder(orderServiceRequest);
    }
}
