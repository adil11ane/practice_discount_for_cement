package com.example;

import com.example.adapters.FileSource;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class FileReaderService {
    private final FileSource fileSource;

    public FileReaderService(FileSource fileSource){
        this.fileSource = fileSource;
    }

    public LinkedList<Order> run(String filePath) throws FileNotFoundException {
        LinkedList<Order> orders = new LinkedList<>();
        orders.addAll(fileSource.getOrders(filePath));
        return orders;
    }


}
