package com.mini.payment.trade.service.impl;

import com.mini.payment.trade.service.PaymentManagerService;
import org.springframework.stereotype.Service;

@Service("paymentManagerService")
public class PaymentManagerServiceImpl implements PaymentManagerService {
    @Override
    public boolean processingTradeRecord(String bankOrderNo) {
        return false;
    }
}
