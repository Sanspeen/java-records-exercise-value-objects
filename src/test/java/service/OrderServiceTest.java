package service;

import org.example.dto.CreateOrderRequest;
import org.example.domain.*;
import org.example.dto.CreateOrderResponse;
import org.example.dto.OrderItemRequest;
import org.example.dto.OrderItemResponse;
import org.example.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class OrderServiceTest {

    OrderService orderService = new OrderService();

    @Test
    public void shouldCreateOrderSuccessfully() {
        List<OrderItemRequest> items = List.of(
                new OrderItemRequest("id-1", 2, 1200),
                new OrderItemRequest("id-2", 1, 2000)
        );

        CreateOrderRequest orderServiceRequest = new CreateOrderRequest(
                "Santiago Franco",
                "santiago@gmail.com",
                "123 Main street",
                "New york",
                "Colombia",
                items,
                PaymentMethods.CARD
        );

        List<OrderItemResponse> itemsValueObject = List.of(
                new OrderItemResponse(
                        new ProductId("id-1"),
                        new Quantity(2),
                        new Money(1200)
                ),
                new OrderItemResponse(
                        new ProductId("id-2"),
                        new Quantity(1),
                        new Money(2000)
                )
        );

        double subtotal = (2 * 1200) + (1 * 2000); // 4400
        double tax = subtotal * 0.19;              // 836
        double total = subtotal + tax;             // 5236

        CreateOrderResponse actualOrderResponse = orderService.createOrder(orderServiceRequest);

        CreateOrderResponse expectedOrderResponse = new CreateOrderResponse(
                actualOrderResponse.orderId(),
                new CustomerName("Santiago Franco"),
                new EmailAddress("santiago@gmail.com"),
                new Address("123 Main street", "New york", "Colombia"),
                itemsValueObject,
                new PaymentMethod(PaymentMethods.CARD),
                total
        );

        Assertions.assertNotNull(actualOrderResponse);
        Assertions.assertEquals(expectedOrderResponse, actualOrderResponse);
    }

    @Test
    public void shouldFail_whenEmailIsWrong(){
        List<OrderItemRequest> items = List.of(
                new OrderItemRequest("id-1", 2, 1200),
                new OrderItemRequest("id-2", 1, 2000)
        );

        CreateOrderRequest orderServiceRequest = new CreateOrderRequest(
                "Santiago Franco",
                "santiagogmail.com",
                "123 Main street",
                "New york",
                "Colombia",
                items,
                PaymentMethods.CARD
        );

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(orderServiceRequest)
        );
    }

    @Test
    public void shouldFail_WhenCustomerNameIsNull(){
        List<OrderItemRequest> items = List.of(
                new OrderItemRequest("id-1", 2, 1200),
                new OrderItemRequest("id-2", 1, 2000)
        );

        CreateOrderRequest orderServiceRequest = new CreateOrderRequest(
                null,
                "santiagogmail.com",
                "123 Main street",
                "New york",
                "Colombia",
                items,
                PaymentMethods.CARD
        );

        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                () -> orderService.createOrder(orderServiceRequest)
        );
    }
}

