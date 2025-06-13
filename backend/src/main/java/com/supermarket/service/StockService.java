package com.supermarket.service;

import com.supermarket.entity.Stock;
import com.supermarket.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private NotificationService notificationService;

    public Stock saveStock(Stock stock) {
        Stock savedStock = stockRepository.save(stock);
        checkLowStock(savedStock);
        return savedStock;
    }

    public void uploadStockFile(MultipartFile file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Stock stock = new Stock();
            stock.setProductName(data[0]);
            stock.setBarcode(data[1]);
            stock.setQuantity(Integer.parseInt(data[2]));
            stock.setPrice(Double.parseDouble(data[3]));
            stock.setStatus(Stock.StockStatus.INSTOCK);
            saveStock(stock);
        }
    }

    @Scheduled(fixedRate = 18000000) // 5 hours in milliseconds
    public void checkLowStockLevels() {
        List<Stock> lowStocks = stockRepository.findByQuantityLessThan(10);
        for (Stock stock : lowStocks) {
            notificationService.sendLowStockNotification(stock);
        }
    }

    private void checkLowStock(Stock stock) {
        if (stock.getQuantity() < 10) {
            notificationService.sendLowStockNotification(stock);
        }
    }

    public Stock updateStockStatus(String barcode, Stock.StockStatus status) {
        Stock stock = stockRepository.findByBarcode(barcode);
        if (stock != null) {
            stock.setStatus(status);
            return stockRepository.save(stock);
        }
        return null;
    }

    public Stock findByBarcode(String barcode) {
        return stockRepository.findByBarcode(barcode);
    }
}
