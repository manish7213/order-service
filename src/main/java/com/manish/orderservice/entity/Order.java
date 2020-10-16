package com.manish.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ORDER_TB")
public class Order {

    @Id
    private Long id;
    private String name;
    private BigInteger qty;
    private BigDecimal price;
}
