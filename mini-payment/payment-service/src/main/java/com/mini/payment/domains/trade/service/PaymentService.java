package com.mini.payment.domains.trade.service;

import com.mini.payment.domains.trade.entity.Payment;

public interface PaymentService {
    Payment saveData(Payment data);

    Payment findById(Long id);
}