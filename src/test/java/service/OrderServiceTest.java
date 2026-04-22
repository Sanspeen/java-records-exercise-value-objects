package service;

import org.example.CreateOrderRequest;
import org.example.domain.*;
import org.example.dto.CreateOrderResponse;
import org.example.dto.OrderItemRequest;
import org.example.dto.OrderItemResponse;
import org.example.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OrderServiceTest {

    OrderService orderService = new OrderService();

    @Test
    public void shouldCreateOrderSuccessfully(){
        List<OrderItemRequest> items = List.of(new OrderItemRequest("id-1", 2, 1200), new OrderItemRequest("id-2", 1, 2000));
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

        CreateOrderResponse expectedOrderResponse = new CreateOrderResponse(
                new CustomerName("Santiago"),
                new EmailAddress(),
                new Address(),
                itemsValueObject,
                new PaymentMethod(PaymentMethods.CARD)
        );

        CreateOrderResponse actualOrderResponse = orderService.createOrder(orderServiceRequest);

        Assertions.assertNotNull(actualOrderResponse);
        Assertions.assertEquals(expectedOrderResponse, actualOrderResponse);
    }

    @Test
    public void shouldFail_whenFieldsProvidedWereWrong(){
        List<OrderItemRequest> items = List.of(new OrderItemRequest("id-1", 0, -1200), new OrderItemRequest("id-2", 1, 2000));
        CreateOrderRequest orderServiceRequest = new CreateOrderRequest(
                null,
                "santiagogmail.com",
                "123 Main street",
                "New york",
                "Colombia",
                items,
                PaymentMethods.CASH
        );
    }

    @Test
    public void shouldFailInOrderCreationByPaymentMethodValidation(){

    }
}
