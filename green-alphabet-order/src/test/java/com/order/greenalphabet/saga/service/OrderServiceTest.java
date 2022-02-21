package com.order.greenalphabet.saga.service;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.model.Status;
import com.order.greenalphabet.saga.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void testFindAll() {
        ArrayList<Order> orderList = new ArrayList<>();
        when(this.orderRepository.findAll()).thenReturn(orderList);
        List<Order> actualFindAllResult = this.orderService.findAll();
        assertSame(orderList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.orderRepository).findAll();
    }

    @Test
    void testPlaceOrder() throws AmqpException {
        doNothing().when(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());

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
        Order actualPlaceOrderResult = this.orderService.placeOrder(order1);
        assertSame(order1, actualPlaceOrderResult);
        assertEquals(Status.APPROVAL_PENDING, actualPlaceOrderResult.getStatus());
        assertEquals("42", actualPlaceOrderResult.getPrice().toString());
        verify(this.rabbitTemplate).convertAndSend((String) any(), (Object) any());
        verify(this.orderRepository).save((Order) any());
        assertTrue(this.orderService.findAll().isEmpty());
    }
}