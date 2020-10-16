package com.manish.orderservice.controller;

import com.manish.orderservice.common.Payment;
import com.manish.orderservice.common.TransactionRequest;
import com.manish.orderservice.common.TransactionResponse;
import com.manish.orderservice.entity.Order;
import com.manish.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<TransactionResponse> placeOrder(@RequestBody TransactionRequest transactionRequest) {

        TransactionResponse transactionResponse = orderService.saveOrder(transactionRequest);
        return new ResponseEntity<>(transactionResponse, HttpStatus.CREATED);
    }
}
