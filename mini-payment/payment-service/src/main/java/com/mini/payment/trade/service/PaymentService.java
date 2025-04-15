package com.mini.payment.trade.service;

import com.mini.payment.trade.entity.Payment;

public interface PaymentService {
    Payment saveData(Payment data);

    Payment findById(Long id);
}