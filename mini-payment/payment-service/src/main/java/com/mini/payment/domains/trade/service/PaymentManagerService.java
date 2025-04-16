package com.mini.payment.domains.trade.service;

import com.mini.payment.domains.notify.entity.MpOrderResultQueryVo;

public interface PaymentManagerService {
    boolean processingTradeRecord(String bankOrderNo);

    void updatePaymentRecordByBankOrderNo(String bankOrderNo, MpOrderResultQueryVo orderQueryVo);
}
