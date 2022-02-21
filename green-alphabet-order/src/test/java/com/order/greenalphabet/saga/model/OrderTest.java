package com.order.greenalphabet.saga.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderTest {
    @Test
    void testConstructor() {
        Order actualOrder = new Order();
        actualOrder.setId(123L);
        actualOrder.setName("Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualOrder.setPrice(valueOfResult);
        actualOrder.setQuantity(1);
        actualOrder.setStatus(Status.APPROVAL_PENDING);
        assertEquals(123L, actualOrder.getId().longValue());
        assertEquals("Name", actualOrder.getName());
        assertSame(valueOfResult, actualOrder.getPrice());
        assertEquals(1, actualOrder.getQuantity().intValue());
        assertEquals(Status.APPROVAL_PENDING, actualOrder.getStatus());
        assertEquals("Order(id=123, name=Name, quantity=1, price=42, status=APPROVAL_PENDING)", actualOrder.toString());
    }

    @Test
    void testConstructor2() {
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        Order actualOrder = new Order(123L, "Name", 1, valueOfResult, Status.APPROVAL_PENDING);
        actualOrder.setId(123L);
        actualOrder.setName("Name");
        BigDecimal valueOfResult1 = BigDecimal.valueOf(42L);
        actualOrder.setPrice(valueOfResult1);
        actualOrder.setQuantity(1);
        actualOrder.setStatus(Status.APPROVAL_PENDING);
        assertEquals(123L, actualOrder.getId().longValue());
        assertEquals("Name", actualOrder.getName());
        BigDecimal price = actualOrder.getPrice();
        assertSame(valueOfResult1, price);
        assertEquals(valueOfResult, price);
        assertEquals(1, actualOrder.getQuantity().intValue());
        assertEquals(Status.APPROVAL_PENDING, actualOrder.getStatus());
        assertEquals("Order(id=123, name=Name, quantity=1, price=42, status=APPROVAL_PENDING)", actualOrder.toString());
    }
}

