package com.mini.payment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "mq.queue-names")
public class MqProperties {
    private String tradeNotify;
    private String orderQuery;

    public String getTradeNotify() {
        return tradeNotify;
    }

    public void setTradeNotify(String tradeNotify) {
        this.tradeNotify = tradeNotify;
    }

    public String getOrderQuery() {
        return orderQuery;
    }

    public void setOrderQuery(String orderQuery) {
        this.orderQuery = orderQuery;
    }
}