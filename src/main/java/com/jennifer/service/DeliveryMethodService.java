package com.jennifer.service;

import com.jennifer.entity.DeliveryMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Providing service for DeliveryMothod
 */
public interface DeliveryMethodService {
    List<DeliveryMethod> findAllDeliveryMethods();
    DeliveryMethod findById(int id);
    DeliveryMethod update(DeliveryMethod deliveryMethod);
    void delete(DeliveryMethod deliveryMethod);
    DeliveryMethod insert(DeliveryMethod deliveryMethod);
}