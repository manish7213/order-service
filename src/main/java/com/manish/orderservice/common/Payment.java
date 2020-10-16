package com.manish.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;
    @Enumerated(EnumType.STRING)
    private PAYMENT_STATUS paymentStatus;
    private String transactionId;

    private Long orderId;
    private BigDecimal amount;
}
