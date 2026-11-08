package com.example.cement_promo;


import com.example.FileReaderService;
import com.example.Order;
import com.example.adapters.FileSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileReaderServiceTest {
    @Mock
    FileSource fileSource;

    @InjectMocks
    FileReaderService readerService;


    @Test
    void returnsOrdersFromSource() throws FileNotFoundException {
        List<Order> expected = List.of(new Order("A", 100, LocalDateTime.of(2021,2,9,10,0)));
        when(fileSource.getOrders("any.txt")).thenReturn(expected);

        LinkedList<Order> result = readerService.run("any.txt");

        assertEquals(1,result.size());
        assertEquals("A", result.getFirst().getCompany());
    }

    @Test
    void callsFileNotFound() throws FileNotFoundException {
        when(fileSource.getOrders("missing.txt")).thenThrow(new FileNotFoundException("missing.txt"));
        assertThrows(FileNotFoundException.class, () -> readerService.run("missing.txt"));
    }

    @Test
    void nothingInTheFile() throws FileNotFoundException {
        when(fileSource.getOrders("empty.txt")).thenReturn(new LinkedList<>());
        assertEquals(0,readerService.run("empty.txt").size());
    }


}
