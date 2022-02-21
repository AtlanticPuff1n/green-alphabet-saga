package com.order.greenalphabet.saga.service;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.model.Status;
import com.order.greenalphabet.saga.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderEventService.class})
@ExtendWith(SpringExtension.class)
class OrderEventServiceTest {
    @Autowired
    private OrderEventService orderEventService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void testProcessOutOfStock() {
        Order order = new Order();
        order.setId(123L);
        order.setName("Name");
        order.setPrice(BigDecimal.valueOf(42L));
        order.setQuantity(1);
        order.setStatus(Status.APPROVAL_PENDING);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Order order1 = new Order();
        order1.setId(123L);
        order1.setName("Name");
        order1.setPrice(BigDecimal.valueOf(42L));
        order1.setQuantity(1);
        order1.setStatus(Status.APPROVAL_PENDING);
        this.orderEventService.processOutOfStock(order1);
        verify(this.orderRepository).save((Order) any());
        assertEquals(Status.REJECTED, order1.getStatus());
    }

    @Test
    void testProcessStockUpdated() {
        Order order = new Order();
        order.setId(123L);
        order.setName("Name");
        order.setPrice(BigDecimal.valueOf(42L));
        order.setQuantity(1);
        order.setStatus(Status.APPROVAL_PENDING);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Order order1 = new Order();
        order1.setId(123L);
        order1.setName("Name");
        order1.setPrice(BigDecimal.valueOf(42L));
        order1.setQuantity(1);
        order1.setStatus(Status.APPROVAL_PENDING);
        this.orderEventService.processStockUpdated(order1);
        verify(this.orderRepository).save((Order) any());
        assertEquals(Status.APPROVED, order1.getStatus());
    }
}