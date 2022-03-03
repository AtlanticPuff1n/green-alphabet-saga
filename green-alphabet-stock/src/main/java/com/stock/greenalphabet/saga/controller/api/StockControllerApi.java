package com.stock.greenalphabet.saga.controller.api;

import com.stock.greenalphabet.saga.model.Stock;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public interface StockControllerApi {

    @ApiOperation(value = "Returns all products")
    @GetMapping
    List<Stock> findAll();

    @ApiOperation(value = "Add a product")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Product successfully added")
            }
    )
    @PostMapping("/add")
    Stock addProduct(@RequestBody Stock stock);
}
