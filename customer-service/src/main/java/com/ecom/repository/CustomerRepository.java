package com.ecom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
