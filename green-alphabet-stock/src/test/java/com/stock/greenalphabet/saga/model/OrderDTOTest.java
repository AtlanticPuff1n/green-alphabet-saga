package com.stock.greenalphabet.saga.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class OrderDTOTest {

    @Test
    void testConstructor() {
        OrderDTO actualOrderDTO = new OrderDTO();
        actualOrderDTO.setId("42");
        actualOrderDTO.setName("Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        actualOrderDTO.setPrice(valueOfResult);
        actualOrderDTO.setQuantity(1);
        actualOrderDTO.setStatus("Status");
        assertEquals("42", actualOrderDTO.getId());
        assertEquals("Name", actualOrderDTO.getName());
        assertSame(valueOfResult, actualOrderDTO.getPrice());
        assertEquals(1, actualOrderDTO.getQuantity().intValue());
        assertEquals("Status", actualOrderDTO.getStatus());
        assertEquals("OrderDTO(id=42, name=Name, quantity=1, price=42, status=Status)", actualOrderDTO.toString());
    }

    @Test
    void testConstructor2() {
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        OrderDTO actualOrderDTO = new OrderDTO("42", "Name", 1, valueOfResult, "Status");
        actualOrderDTO.setId("42");
        actualOrderDTO.setName("Name");
        BigDecimal valueOfResult1 = BigDecimal.valueOf(42L);
        actualOrderDTO.setPrice(valueOfResult1);
        actualOrderDTO.setQuantity(1);
        actualOrderDTO.setStatus("Status");
        assertEquals("42", actualOrderDTO.getId());
        assertEquals("Name", actualOrderDTO.getName());
        BigDecimal price = actualOrderDTO.getPrice();
        assertSame(valueOfResult1, price);
        assertEquals(valueOfResult, price);
        assertEquals(1, actualOrderDTO.getQuantity().intValue());
        assertEquals("Status", actualOrderDTO.getStatus());
        assertEquals("OrderDTO(id=42, name=Name, quantity=1, price=42, status=Status)", actualOrderDTO.toString());
    }

}