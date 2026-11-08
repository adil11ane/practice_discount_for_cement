package com.example;

import java.time.LocalDateTime;

public class Order {
    private String company;
    private int quantity;
    private LocalDateTime date;

    public Order(String company, int quantity, LocalDateTime date) {
        this.company = company;
        this.quantity = quantity;
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
