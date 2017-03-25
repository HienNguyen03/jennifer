package com.jennifer.service;

import com.jennifer.entity.PaymentInvoice;

import java.util.List;

/**
 * Providing service for PaymentInvoice
 */
public interface PaymentInvoiceService {

    List<PaymentInvoice> findAll();
    PaymentInvoice findById(int id);
    PaymentInvoice insert(PaymentInvoice paymentInvoice);
    PaymentInvoice update(PaymentInvoice paymentInvoice);

}
