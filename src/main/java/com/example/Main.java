package com.example;


import com.example.adapters.*;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        double pricePerKg = 1.2;
        double startDiscountPercent = 50.0;
        double discountStepPercent = 5.0;

        process(new TxtFileAdapter(), "discount_day.txt", "result_txt.txt",
                pricePerKg,startDiscountPercent,discountStepPercent);

        process(new PlainFileAdapter(), "discount_day_without_ext", "result_without_txt_ext_case.txt",
                pricePerKg,startDiscountPercent,discountStepPercent);
    }

    private static void process(FileSource source, String inputPath, String outputPath,
                                double pricePerKg, double startDiscountPercent, double discountStepPercent) {
        FileReaderService readerService = new FileReaderService(source);
        SortingService sortingService = new SortingService(readerService);
        DiscountApplianceService discountService =
                new DiscountApplianceService(sortingService, startDiscountPercent, discountStepPercent, pricePerKg);
        ResultWriter writer = new TotalResultWirter();

        try {
            Map<String, Double> totals = discountService.applyDiscount(inputPath);
            writer.write(outputPath, totals);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage()) ;
        }

    }
}