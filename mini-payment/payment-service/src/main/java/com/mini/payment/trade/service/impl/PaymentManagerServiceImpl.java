package com.mini.payment.trade.service.impl;

import com.mini.payment.app.notify.entity.MpOrderResultQueryVo;
import com.mini.payment.trade.entity.Payment;
import com.mini.payment.trade.repository.PaymentRepository;
import com.mini.payment.trade.service.PaymentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("paymentManagerService")
public class PaymentManagerServiceImpl implements PaymentManagerService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean processingTradeRecord(String bankOrderNo) {
        List<Payment> payments = paymentRepository.findAllByBankOrderNo(bankOrderNo);
        return Objects.nonNull(payments) && payments.size() > 0;
    }

    @Override
    public void updatePaymentRecordByBankOrderNo(String bankOrderNo, MpOrderResultQueryVo orderQueryVo) {
        Payment payment = paymentRepository.findByBankOrderNo(bankOrderNo);
        payment.setDescription("Done");
        paymentRepository.save(payment);
    }
}
