package com.mini.payment.config;

public class MqConfig {

    public static String MERCHANT_NOTIFY_QUEUE = "";
    public static String ORDER_NOTIFY_QUEUE = "";

    public static void init(MqProperties properties) {
        MERCHANT_NOTIFY_QUEUE = properties.getTradeNotify();
        ORDER_NOTIFY_QUEUE = properties.getOrderQuery();
    }
}