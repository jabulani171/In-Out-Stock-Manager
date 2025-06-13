package com.supermarket.controller;

import com.supermarket.entity.Sale;
import com.supermarket.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/process")
    public Sale processSale(@RequestBody Sale sale) {
        return saleService.processSale(sale);
    }

    @GetMapping("/daily-total")
    public double getDailyTotal(@RequestParam LocalDate date) {
        return saleService.getDailyTotal(date);
    }
}
