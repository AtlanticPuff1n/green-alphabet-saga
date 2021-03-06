package com.order.greenalphabet.saga.service;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.model.Status;
import com.order.greenalphabet.saga.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderEventService {
    private OrderRepository orderRepository;

    public void processOutOfStock(Order order) {
        order.setStatus(Status.REJECTED);
        orderRepository.save(order);
    }

    public void processStockUpdated(Order order) {
        order.setStatus(Status.APPROVED);
        orderRepository.save(order);
    }
}
