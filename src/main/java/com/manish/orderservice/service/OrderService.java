package com.manish.orderservice.service;

import com.manish.orderservice.common.PAYMENT_STATUS;
import com.manish.orderservice.common.Payment;
import com.manish.orderservice.common.TransactionRequest;
import com.manish.orderservice.common.TransactionResponse;
import com.manish.orderservice.entity.Order;
import com.manish.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {

        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public TransactionResponse saveOrder(TransactionRequest transactionRequest) {

        log.info("OrderService : saveOrder(-)");
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        //eureka implemented: calling the service from taking the host and port from eureka
        Payment paymentResponse = restTemplate.postForObject("http://payment-service/api/v1/payment/performPayment", payment, Payment.class);
        String responseMessage = paymentResponse
                .getPaymentStatus()
                .equals(PAYMENT_STATUS.SUCCESS) ? "Payment Success" : "Payment Failed";
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), responseMessage);
        //As soon as order is placed , it should go to payment service, hence do a rest call.
    }
}
