package com.mini.payment.app.polling.core;

import com.mini.payment.app.notify.entity.MpOrderResultQueryVo;
import com.mini.payment.exception.BizException;
import com.mini.payment.trade.service.PaymentManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("pollingPersist")
public class PollingPersist {
    private static final Logger LOG = LoggerFactory.getLogger(PollingPersist.class);

    @Autowired
    private PollingQueue pollingQueue;

    @Autowired
    private PaymentManagerService paymentManagerService;

    public void getOrderResult(MpOrderResultQueryVo orderQueryVo) {
        Integer notifyTimes = orderQueryVo.getNotifyTimes();
        Integer maxNotifyTimes = orderQueryVo.getLimitNotifyTimes();
        Date notifyTime = new Date();
        orderQueryVo.setEditTime(notifyTime);
        orderQueryVo.setNotifyTimes(notifyTimes + 1);

        LOG.info("notifyTimes:{}  , maxNotifyTimes:{} ", notifyTimes, maxNotifyTimes);
        try {
            boolean processingResult = paymentManagerService.processingTradeRecord(orderQueryVo.getBankOrderNo());

            LOG.info("order processing result:{}", processingResult);
            if (!processingResult) {
                if (orderQueryVo.getNotifyTimes() < maxNotifyTimes) {
                    pollingQueue.addItemToTaskDelayQueue(orderQueryVo);
                    LOG.info("===>bank order {} need processing again ", orderQueryVo.getBankOrderNo());
                } else {
                    LOG.info("bank order No {} not pay", orderQueryVo.getBankOrderNo());
                }
            }

        } catch (BizException e) {
            LOG.error("order handle business logic error:", e);
            if (orderQueryVo.getNotifyTimes() < maxNotifyTimes) {
                pollingQueue.addItemToTaskDelayQueue(orderQueryVo);
                LOG.info("bank order {} need processing again ", orderQueryVo.getBankOrderNo());
            } else {
                LOG.info("bank order No {} not pay", orderQueryVo.getBankOrderNo());
            }
        } catch (Exception e) {
            LOG.error("order handle logic error:", e);
            if (orderQueryVo.getNotifyTimes() < maxNotifyTimes) {
                pollingQueue.addItemToTaskDelayQueue(orderQueryVo);
                LOG.info("bank order {} need processing again ", orderQueryVo.getBankOrderNo());
            } else {
                LOG.info("bank order No {} not pay", orderQueryVo.getBankOrderNo());
            }
        }
    }
}
