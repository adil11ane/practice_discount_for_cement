package com.example.adapters;

import com.example.Order;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileSource {
    List<Order> getOrders(String filePath) throws FileNotFoundException;
}
