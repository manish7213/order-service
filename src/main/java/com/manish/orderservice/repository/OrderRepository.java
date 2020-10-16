package com.manish.orderservice.repository;

import com.manish.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
