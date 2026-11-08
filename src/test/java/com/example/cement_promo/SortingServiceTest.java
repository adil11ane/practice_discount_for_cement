package com.example.cement_promo;

import com.example.FileReaderService;
import com.example.Order;
import com.example.SortingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class SortingServiceTest {
    @Mock
    private  FileReaderService readerService;

    private Order order(String company, int hour){
        return new Order(company,100, LocalDateTime.of(2021,2,9,hour,0));
    }

    @Test
    void sortsOrdersByDateFromEarliestToLatest() throws FileNotFoundException {
        LinkedList<Order> unsorted = new LinkedList<>();
        unsorted.add(order("A",11));
        unsorted.add(order("B",12));
        unsorted.add(order("C",10));

        when(readerService.run("orders.txt")).thenReturn(unsorted);

        SortingService sortingService = new SortingService(readerService);

        LinkedList<Order> result = sortingService.sortingOrders("orders.txt");
        List<String> companies = result.stream().map(Order::getCompany).toList();

        assertEquals(List.of("C","A","B"), companies);
    }

    @Test
    void returnsEmptyListIfNoOrders() throws FileNotFoundException {
        when(readerService.run("empty.txt")).thenReturn(new LinkedList<>());
        SortingService sortingService = new SortingService(readerService);
        assertEquals(0,sortingService.sortingOrders("empty.txt").size());
    }

    @Test
    void passesFIleNotFound() throws FileNotFoundException {
        when(readerService.run("missing.txt")).thenThrow(new FileNotFoundException("missing.txt"));
        SortingService sortingService = new SortingService(readerService);

        assertThrows(FileNotFoundException.class,
                () -> sortingService.sortingOrders("missing.txt"));
    }
}
