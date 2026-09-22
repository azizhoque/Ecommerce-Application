package com.ecom.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.order.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

}
