package com.mini.payment.domain.trade.service;

import com.mini.payment.domain.trade.entity.Payment;

public interface PaymentService {
    Payment saveData(Payment data);

    Payment findById(Long id);
}