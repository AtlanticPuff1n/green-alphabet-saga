package com.order.greenalphabet.saga.controller.impl;

import com.order.greenalphabet.saga.controller.api.OrderControllerApi;
import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderControllerApi {
    private OrderService orderService;

    @Override
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @Override
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}

