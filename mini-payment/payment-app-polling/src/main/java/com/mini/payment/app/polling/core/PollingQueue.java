package com.mini.payment.app.polling.core;

import com.mini.payment.domain.notify.entity.MpOrderResultQueryVo;
import com.mini.payment.app.polling.OrderPollingRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class PollingQueue  {
    private static final Logger LOG = LoggerFactory.getLogger(PollingQueue.class);

    public void addItemToTaskDelayQueue(MpOrderResultQueryVo orderQueryVo) {
        if (Objects.isNull(orderQueryVo)) {
            return;
        }
        Integer notifyTimes = orderQueryVo.getNotifyTimes();
        Integer maxNotifyTimes = orderQueryVo.getLimitNotifyTimes();

        if (orderQueryVo.getNotifyTimes().intValue() == 0) {
            orderQueryVo.setLastNotifyTime(new Date());
        } else {
            orderQueryVo.setLastNotifyTime(orderQueryVo.getEditTime());
        }

        if (notifyTimes < maxNotifyTimes) {
            LOG.info("bank order no {}", orderQueryVo.getBankOrderNo());
            OrderPollingRunner.tasks.put(new PollingTask(orderQueryVo));
        }
    }
}
