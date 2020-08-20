package com.sales.controller;

import com.sales.response.StatsResponse;
import com.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping(value = "/sales", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity ingestSales(@RequestParam("sales_amount") Double sales_amount) {
        salesService.addSalesData(sales_amount);
        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatsResponse> getStats() {
        return new ResponseEntity<>(salesService.getStatsData(), HttpStatus.OK);
    }

}
