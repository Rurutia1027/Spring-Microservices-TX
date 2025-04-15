package com.mini.payment.trade.service.impl;

import com.mini.payment.trade.entity.Payment;
import com.mini.payment.trade.repository.PaymentRepository;
import com.mini.payment.trade.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment saveData(Payment data) {
        return paymentRepository.save(data);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}