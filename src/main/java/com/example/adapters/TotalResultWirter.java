package com.example.adapters;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TotalResultWirter implements ResultWriter{
    @Override
    public void write(String path, Map<String, Double> totals) throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(Path.of(path))){
            for(Map.Entry<String, Double> entry : totals.entrySet()){
                writer.write(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        }
    }

}
