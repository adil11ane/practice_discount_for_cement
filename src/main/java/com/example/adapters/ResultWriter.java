package com.example.adapters;

import java.io.IOException;
import java.util.Map;

public interface ResultWriter {
    void write(String path, Map<String, Double> totals)throws IOException;
}
