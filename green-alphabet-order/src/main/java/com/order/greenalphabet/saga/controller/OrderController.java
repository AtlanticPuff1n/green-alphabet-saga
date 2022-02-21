package com.order.greenalphabet.saga.controller;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @PostMapping("/add")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}

