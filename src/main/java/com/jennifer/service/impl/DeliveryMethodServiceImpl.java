package com.jennifer.service.impl;

import com.jennifer.controller.rest.RestDeliveryMethodController;
import com.jennifer.dao.DeliveryMethodDao;
import com.jennifer.entity.DeliveryMethod;
import com.jennifer.service.DeliveryMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DeliveryMethodService implementation
 */
@Service("deliveryMethodService")
@Transactional
public class DeliveryMethodServiceImpl implements DeliveryMethodService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryMethodServiceImpl.class);
    private DeliveryMethodDao deliveryMethodDao;

    @Autowired
    public DeliveryMethodServiceImpl(DeliveryMethodDao deliveryMethodDao) {
        this.deliveryMethodDao = deliveryMethodDao;
    }

    @Override
    public List<DeliveryMethod> findAllDeliveryMethods() {
        return deliveryMethodDao.findAll();
    }

    @Override
    public DeliveryMethod findById(int id) {
        return deliveryMethodDao.findById(id);
    }


    @Override
    public DeliveryMethod update(DeliveryMethod deliveryMethod) {
        return deliveryMethodDao.save(deliveryMethod);
    }

    @Override
    public void delete(DeliveryMethod deliveryMethod) {
        deliveryMethodDao.delete(deliveryMethod);
    }

    @Override
    public DeliveryMethod insert(DeliveryMethod deliveryMethod) {
        return deliveryMethodDao.save(deliveryMethod);
    }
}
