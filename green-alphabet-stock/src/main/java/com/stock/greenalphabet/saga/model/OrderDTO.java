package com.stock.greenalphabet.saga.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String status;
}
