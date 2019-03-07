package com.edu.eteration.demo.productservice;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    public OrderResultDTO createOrder(OrderRequestDTO order) throws InterruptedException, ExecutionException {
        OrderResultDTO or = new OrderResultDTO();
        return or;
    }
}
