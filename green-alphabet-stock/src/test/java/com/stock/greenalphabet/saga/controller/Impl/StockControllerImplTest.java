package com.stock.greenalphabet.saga.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.greenalphabet.saga.controller.Impl.StockControllerImpl;
import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.service.StockService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StockControllerImpl.class})
@ExtendWith(SpringExtension.class)
class StockControllerImplTest {
    @Autowired
    private StockControllerImpl stockControllerImpl;

    @MockBean
    private StockService stockService;

    @Test
    void testAddProduct() throws Exception {
        Stock stock = new Stock();
        stock.setId(123L);
        stock.setName("Name");
        stock.setPrice(BigDecimal.valueOf(42L));
        stock.setQuantity(1);
        when(this.stockService.save((Stock) any())).thenReturn(stock);

        Stock stock1 = new Stock();
        stock1.setId(123L);
        stock1.setName("Name");
        stock1.setPrice(BigDecimal.valueOf(42L));
        stock1.setQuantity(1);
        String content = (new ObjectMapper()).writeValueAsString(stock1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.stockControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\",\"quantity\":1,\"price\":42}"));
    }

    @Test
    void testFindAll() throws Exception {
        when(this.stockService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock");
        MockMvcBuilders.standaloneSetup(this.stockControllerImpl)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindAll2() throws Exception {
        when(this.stockService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/stock");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.stockControllerImpl)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}