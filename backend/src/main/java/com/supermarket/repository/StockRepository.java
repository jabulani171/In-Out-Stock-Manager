package com.supermarket.repository;

import com.supermarket.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByBarcode(String barcode);
    List<Stock> findByQuantityLessThan(int quantity);
}
