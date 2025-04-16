package com.mini.payment.domain.trade.service;

import com.mini.payment.domain.notify.entity.MpOrderResultQueryVo;

public interface PaymentManagerService {
    boolean processingTradeRecord(String bankOrderNo);

    void updatePaymentRecordByBankOrderNo(String bankOrderNo, MpOrderResultQueryVo orderQueryVo);
}
