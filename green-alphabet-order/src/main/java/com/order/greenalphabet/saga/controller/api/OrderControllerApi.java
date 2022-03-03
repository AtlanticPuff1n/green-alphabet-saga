package com.order.greenalphabet.saga.controller.api;

import com.order.greenalphabet.saga.model.Order;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public interface OrderControllerApi {

    @ApiOperation(value = "Returns all orders")
    @GetMapping
    List<Order> findAll();

    @ApiOperation(value = "Place an order")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Order successfully placed")
            }
    )
    @PostMapping("/add")
    Order placeOrder(@RequestBody Order order);
}
