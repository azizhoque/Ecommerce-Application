package com.ecom.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.payment.model.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

}
