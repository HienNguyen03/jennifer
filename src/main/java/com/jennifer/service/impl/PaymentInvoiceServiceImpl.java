package com.jennifer.service.impl;

import com.jennifer.dao.PaymentInvoiceDao;
import com.jennifer.entity.PaymentInvoice;
import com.jennifer.service.PaymentInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PaymentInvoiceService implementation
 */

@Service("paymentInvoiceService")
public class PaymentInvoiceServiceImpl implements PaymentInvoiceService {
    private static final Logger log = LoggerFactory.getLogger(PaymentInvoiceServiceImpl.class);

    private PaymentInvoiceDao paymentInvoiceDao;

    @Autowired
    public PaymentInvoiceServiceImpl(PaymentInvoiceDao paymentInvoiceDao) {
        this.paymentInvoiceDao = paymentInvoiceDao;
    }

    @Override
    public List<PaymentInvoice> findAll() {
        return paymentInvoiceDao.findAll();
    }

    @Override
    public PaymentInvoice findById(int id) {
        return paymentInvoiceDao.findById(id);
    }

    @Override
    public PaymentInvoice insert(PaymentInvoice paymentInvoice) {
        return paymentInvoiceDao.save(paymentInvoice);
    }

    @Override
    public PaymentInvoice update(PaymentInvoice paymentInvoice) {
        return paymentInvoiceDao.save(paymentInvoice);
    }
}


