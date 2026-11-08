package com.example.adapters;

import com.example.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlainFileAdapter implements FileSource{
    @Override
    public List<Order> getOrders(String filePath) {
        List<Order> orders = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ( (line = br.readLine()) != null) {
                String[] parts = line.split("\\#");
                LocalDateTime time = LocalDateTime.parse(parts[0].trim());
                String company = parts[1].trim();
                int kg = Integer.parseInt(parts[2].trim());
                orders.add(new Order(company, kg, time));
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return orders;
    }
}
