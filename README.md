# Java Records Exercise — DTOs and Value Objects

## Objective

The goal of this exercise is to demonstrate understanding of the following rule:

> In Java, all DTOs and Value Objects should be modeled as `record`, not as mutable classes with getters and setters.

This exercise implements a simple order creation flow for an online store using Java Records.

---

## Problem Statement

Build a small order creation module where a client sends an order request and the system:

- validates the input
- transforms raw input into Value Objects
- calculates subtotal, tax, and total
- returns an order response

The focus is not on frameworks, persistence, or APIs.  
The focus is on **correct modeling with Java Records**.

---

## Business Context

The system receives an order request with:

- customer name
- email
- shipping address
- list of items
- payment method

Then it must:

1. validate the data
2. build the corresponding domain objects
3. calculate the total order value
4. return a response with the created order information

---

## Main Rule of the Exercise

The following types must be implemented as `record`:

- **DTOs**
- **Value Objects**

Do **not** use JavaBean-style classes with mutable fields, setters, or Lombok-generated boilerplate for these objects.

---

## Project Scope

### DTOs

Use records for request/response models such as:

- `CreateOrderRequest`
- `OrderItemRequest`
- `CreateOrderResponse`
- `OrderItemResponse`

### Value Objects

Use records for domain concepts such as:

- `CustomerName`
- `EmailAddress`
- `Address`
- `ProductId`
- `Quantity`
- `Money`
- `PaymentMethod`

---

## Functional Requirements

### Input

The application must accept an order request with:

- customer name
- email
- street
- city
- country
- list of products
- payment method

Each order item must contain:

- product id
- quantity
- unit price

---

## Validation Rules

The solution must validate the following:

- customer name cannot be null or blank
- email must contain `@`
- quantity must be greater than `0`
- money amount cannot be negative
- payment method must be valid

---

## Business Logic

The order service must:

1. receive a `CreateOrderRequest`
2. transform primitive values into Value Objects
3. calculate subtotal
4. calculate tax (`19%`)
5. calculate total
6. generate an order id
7. return a `CreateOrderResponse`

---

## Expected Design Intent

This exercise is meant to show that:

- DTOs are immutable data carriers
- Value Objects are immutable and expressive
- `record` is a good fit for both DTOs and Value Objects
- validation can live inside the compact constructor of a record
- records can still contain behavior through instance methods when useful

---

## Example Design Ideas

### Example of a Value Object as a Record

```java
public record EmailAddress(String value) {
    public EmailAddress {
        if (value == null || value.isBlank() || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}