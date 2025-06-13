package com.supermarket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Stock> items;
    private double totalAmount;
    private double amountReceived;
    private double change;
    private LocalDateTime saleDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public List<Stock> getItems() { return items; }
    public void setItems(List<Stock> items) { this.items = items; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public double getAmountReceived() { return amountReceived; }
    public void setAmountReceived(double amountReceived) { this.amountReceived = amountReceived; }
    public double getChange() { return change; }
    public void setChange(double change) { this.change = change; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }
}
