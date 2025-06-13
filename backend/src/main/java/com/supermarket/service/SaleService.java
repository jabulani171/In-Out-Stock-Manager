package com.supermarket.service;

import com.supermarket.entity.Sale;
import com.supermarket.entity.Stock;
import com.supermarket.repository.SaleRepository;
import com.supermarket.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StockService stockService;

    public Sale processSale(Sale sale) {
        sale.setSaleDate(LocalDateTime.now());
        for (Stock item : sale.getItems()) {
            stockService.updateStockStatus(item.getBarcode(), Stock.StockStatus.SOLD);
        }
        return saleRepository.save(sale);
    }

    public double getDailyTotal(LocalDate date) {
        List<Sale> sales = saleRepository.findBySaleDateBetween(
            date.atStartOfDay(), date.plusDays(1).atStartOfDay()
        );
        return sales.stream().mapToDouble(Sale::getTotalAmount).sum();
    }
}
