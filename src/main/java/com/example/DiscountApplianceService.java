package com.example;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscountApplianceService {
    private final SortingService sortingService;
    private final double startingDiscountPercent;
    private final double discountStepPercent;
    private final double pricePerKg;

    public DiscountApplianceService(SortingService sortingService, double startingDiscountPercent, double discountStepPercent, double pricePerKg) {
        this.sortingService = sortingService;
        this.startingDiscountPercent = startingDiscountPercent;
        this.discountStepPercent = discountStepPercent;
        this.pricePerKg = pricePerKg;
    }

    public Map<String, Double> applyDiscount(String filePath) throws FileNotFoundException{
        double discount = startingDiscountPercent;
        Map<String, Double> totals = new LinkedHashMap<>();

        for(Order order : sortingService.sortingOrders(filePath)){
            double cost = order.getQuantity() * pricePerKg * (1 - discount/100.0);
            totals.merge(order.getCompany(), cost, Double::sum);
            discount = Math.max(0, discount - discountStepPercent);
        }

        return totals;
    }

}
