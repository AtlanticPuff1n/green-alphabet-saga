package com.stock.greenalphabet.saga.service;

import com.stock.greenalphabet.saga.model.Stock;
import com.stock.greenalphabet.saga.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }
}

