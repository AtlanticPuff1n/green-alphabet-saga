package com.order.greenalphabet.saga.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.greenalphabet.saga.controller.impl.OrderControllerImpl;
import com.order.greenalphabet.saga.model.Order;
import com.order.greenalphabet.saga.model.Status;
import com.order.greenalphabet.saga.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderControllerImpl.class})
@ExtendWith(SpringExtension.class)
class OrderControllerImplTest {
    @Autowired
    private OrderControllerImpl orderControllerImpl;

    @MockBean
    private OrderService orderService;

    @Test
    void testFindAll() throws Exception {
        when(this.orderService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders");
        MockMvcBuilders.standaloneSetup(this.orderControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindAll2() throws Exception {
        when(this.orderService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/orders");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.orderControllerImpl)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testPlaceOrder() throws Exception {
        Order order = new Order();
        order.setId(123L);
        order.setName("Name");
        order.setPrice(BigDecimal.valueOf(42L));
        order.setQuantity(1);
        order.setStatus(Status.APPROVAL_PENDING);
        when(this.orderService.placeOrder((Order) any())).thenReturn(order);

        Order order1 = new Order();
        order1.setId(123L);
        order1.setName("Name");
        order1.setPrice(BigDecimal.valueOf(42L));
        order1.setQuantity(1);
        order1.setStatus(Status.APPROVAL_PENDING);
        String content = (new ObjectMapper()).writeValueAsString(order1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.orderControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"name\":\"Name\",\"quantity\":1,\"price\":42,\"status\":\"APPROVAL_PENDING\"}"));
    }
}
