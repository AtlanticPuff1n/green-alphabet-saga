package com.stock.greenalphabet.saga.controller;

import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class StockController {
    private StockService stockService;

    @GetMapping
    public List<Stock> findAll() {
        return stockService.findAll();
    }

    @PostMapping("/add")
    public Stock addProduct(@RequestBody Stock stock) {
        return stockService.save(stock);
    }
}
