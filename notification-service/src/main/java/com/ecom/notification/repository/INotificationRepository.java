package com.ecom.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.notification.model.Notification;

@Repository
public interface INotificationRepository extends MongoRepository<Notification, String> {

}
