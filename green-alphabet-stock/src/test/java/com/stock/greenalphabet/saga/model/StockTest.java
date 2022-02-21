package com.stock.greenalphabet.saga.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class StockTest {
    @Test
    void testConstructor() {
        Stock actualStock = new Stock();
        actualStock.setId(123L);
        actualStock.setName("Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualStock.setPrice(valueOfResult);
        actualStock.setQuantity(1);
        assertEquals(123L, actualStock.getId().longValue());
        assertEquals("Name", actualStock.getName());
        assertSame(valueOfResult, actualStock.getPrice());
        assertEquals(1, actualStock.getQuantity().intValue());
        assertEquals("Stock(id=123, name=Name, quantity=1, price=42)", actualStock.toString());
    }

    @Test
    void testConstructor2() {
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        Stock actualStock = new Stock(123L, "Name", 1, valueOfResult);
        actualStock.setId(123L);
        actualStock.setName("Name");
        BigDecimal valueOfResult1 = BigDecimal.valueOf(42L);
        actualStock.setPrice(valueOfResult1);
        actualStock.setQuantity(1);
        assertEquals(123L, actualStock.getId().longValue());
        assertEquals("Name", actualStock.getName());
        BigDecimal price = actualStock.getPrice();
        assertSame(valueOfResult1, price);
        assertEquals(valueOfResult, price);
        assertEquals(1, actualStock.getQuantity().intValue());
        assertEquals("Stock(id=123, name=Name, quantity=1, price=42)", actualStock.toString());
    }
}

