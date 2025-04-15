package com.mini.payment.trade.service;

import com.mini.payment.app.notify.entity.MpOrderResultQueryVo;

public interface PaymentManagerService {
    boolean processingTradeRecord(String bankOrderNo);

    void updatePaymentRecordByBankOrderNo(String bankOrderNo, MpOrderResultQueryVo orderQueryVo);
}
