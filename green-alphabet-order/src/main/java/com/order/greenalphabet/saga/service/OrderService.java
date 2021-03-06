package com.order.greenalphabet.saga.service;

import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.model.Status;
import com.order.greenalphabet.saga.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.order.greenalphabet.saga.constants.Constants.ORDER_CREATED_NAME;

@Service
@AllArgsConstructor
public class OrderService {
    private RabbitTemplate template;
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order placeOrder(Order order) {
        order.setStatus(Status.APPROVAL_PENDING);
        orderRepository.save(order);
        template.convertAndSend(ORDER_CREATED_NAME, order);
        return order;
    }
}
