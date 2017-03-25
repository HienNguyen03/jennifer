package com.jennifer.dao;

import com.jennifer.entity.PaymentInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderInfo data access object interface
 */
@Repository
public interface PaymentInvoiceDao extends JpaRepository<PaymentInvoice, Integer> {
    List<PaymentInvoice> findAll();
    PaymentInvoice findById(int id);
}
