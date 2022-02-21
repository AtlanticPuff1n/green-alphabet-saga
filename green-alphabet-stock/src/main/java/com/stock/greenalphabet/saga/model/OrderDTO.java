package com.stock.greenalphabet.saga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String status;
}
