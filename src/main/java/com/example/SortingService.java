package com.example;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;

public class SortingService {
    private final FileReaderService outputService;


    public SortingService(FileReaderService outputService) {
        this.outputService = outputService;
    }

    public LinkedList<Order> sortingOrders(String filePath) throws FileNotFoundException {
        LinkedList<Order> orders = outputService.run(filePath);
        orders.sort(Comparator.comparing(Order::getDate));
        return orders;
    }

}
