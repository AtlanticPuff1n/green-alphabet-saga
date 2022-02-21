package com.stock.greenalphabet.saga.repository;

import com.stock.greenalphabet.saga.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
}
