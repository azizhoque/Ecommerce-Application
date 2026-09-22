package com.ecom.orderline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.orderline.model.OrderLine;

@Repository
public interface IOrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
