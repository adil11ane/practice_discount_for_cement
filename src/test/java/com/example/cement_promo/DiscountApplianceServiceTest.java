package com.example.cement_promo;


import com.example.DiscountApplianceService;
import com.example.Order;
import com.example.SortingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountApplianceServiceTest {
    @Mock
    private SortingService sortingService;

    private DiscountApplianceService service;

    @BeforeEach
    void setUp() {
        service = new DiscountApplianceService(sortingService,50.0,5.0,10.0);
    }

    private Order order(String company, int hour) {
        return new Order(company,10,LocalDateTime.of(2021,2,9,hour,0));
    }
    @Test
    void discountDiscreasesWithEachOrder() throws FileNotFoundException {
        LinkedList<Order> orders = new LinkedList<>();
        orders.add(order("A",10));
        orders.add(order("B",11));
        orders.add(order("C",12));
        when(sortingService.sortingOrders("orders.txt")).thenReturn(orders);

        Map<String, Double> res = service.applyDiscount("orders.txt");
        assertEquals(50.0, res.get("A"),0.001);
        assertEquals(55.0,res.get("B"),0.001);
        assertEquals(60.0,res.get("C"),0.001);
    }

    @Test
    void sumsOrderOfTheSameCompany() throws FileNotFoundException {
        LinkedList<Order> orders = new LinkedList<>();
        orders.add(order("A",10));
        orders.add(order("B",11));
        orders.add(order("A",12));

        when(sortingService.sortingOrders("orders.txt")).thenReturn(orders);
        Map<String, Double> res = service.applyDiscount("orders.txt");

        assertEquals(2,res.size());
        assertEquals(110.0,res.get("A"),0.001);
        assertEquals(55.0, res.get("B"), 0.001);
    }

    @Test
    void discountDoesNotGoBelowZero() throws FileNotFoundException {
        DiscountApplianceService service =
                new DiscountApplianceService(sortingService, 10.0, 5.0, 10.0);
        LinkedList<Order> orders = new LinkedList<>();
        orders.add(order("A", 10));
        orders.add(order("B", 11));
        orders.add(order("C", 12));
        orders.add(order("D", 13));
        when(sortingService.sortingOrders("orders.txt")).thenReturn(orders);
        Map<String, Double> res = service.applyDiscount("orders.txt");

        assertEquals(90.0, res.get("A"), 0.001);
        assertEquals(95.0, res.get("B"), 0.001);
        assertEquals(100.0, res.get("C"), 0.001);
        assertEquals(100.0, res.get("D"), 0.001);
    }
}
