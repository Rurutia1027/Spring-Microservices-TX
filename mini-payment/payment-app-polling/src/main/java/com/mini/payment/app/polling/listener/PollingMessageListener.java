package com.mini.payment.app.polling.listener;

import com.alibaba.fastjson2.JSONObject;
import com.mini.payment.domain.notify.entity.MpOrderResultQueryVo;
import com.mini.payment.domain.notify.enums.NotifyStatusEnum;
import com.mini.payment.app.polling.core.PollingQueue;
import com.mini.payment.app.polling.entity.PollingParam;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component("pollingMessageListener")
public class PollingMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(PollingMessageListener.class);

    @Autowired
    private PollingQueue pollingQueue;

    @Autowired
    private PollingParam pollingParam;


    @Override
    public void onMessage(Message message) {
        try {
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            final String bankOrderNoStr = textMessage.getText();

            // this msg str should be the bank order number(orderNo) value
            LOG.info("#onMessage receive bankOrderNo: {}", bankOrderNoStr);
            if (StringUtils.isBlank(bankOrderNoStr)) {
                LOG.error("#onMessage receive blank bank order no. skip processing");
                return;
            }

            MpOrderResultQueryVo mpOrderResultQueryVo = new MpOrderResultQueryVo();
            mpOrderResultQueryVo.setBankOrderNo(bankOrderNoStr);
            mpOrderResultQueryVo.setStatus(NotifyStatusEnum.CREATED.name());
            mpOrderResultQueryVo.setCreateTime(new Date());
            mpOrderResultQueryVo.setEditTime(new Date());
            mpOrderResultQueryVo.setLastNotifyTime(new Date());
            mpOrderResultQueryVo.setNotifyTimes(0);
            mpOrderResultQueryVo.setLimitNotifyTimes(pollingParam.getMaxNotifyTimes());
            Map<Integer, Integer> notifyStrategy = pollingParam.getNotifyParams();
            mpOrderResultQueryVo.setNotifyStrategy(JSONObject.toJSONString(notifyStrategy));

            // appending this order result to polling-queue
            try {
                // this is the first tome to commit order result to polling-queue
                pollingQueue.addItemToTaskDelayQueue(mpOrderResultQueryVo);
            } catch (RuntimeException e) {
                LOG.error("#onMessage got exception when appending order vo object to " +
                        "polling queue");
            }
        } catch (Exception e) {
            LOG.error("#onMessage got exception! ", e);
        }
    }
}
