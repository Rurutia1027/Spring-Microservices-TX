package com.mini.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PayTypeEnum {
    SCANPAY("WEIXIN", "weixin scan code pay"),
    H5PAY("WEIXIN", "H5 payment"),
    DIRECT_PAY("ALIPAY", "alipay payment"),
    ALI_TEST("ALIPAY", "alipay test"),
    TEST_PAY("TEST_PAY", "test pay"),
    TEST_PAY_HTTP_CLIENT("TEST_PAY_HTTP_CLIENT", "mock http client payment");

    private String paymentPlatform;

    private String desc;

    private PayTypeEnum(String paymentPlatform, String desc) {
        this.paymentPlatform = paymentPlatform;
        this.desc = desc;
    }

    public String getPaymentPlatform() {
        return paymentPlatform;
    }

    public void setPaymentPlatform(String paymentPlatform) {
        this.paymentPlatform = paymentPlatform;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PayTypeEnum[] arr = PayTypeEnum.values();
        Map<String, Map<String, Object>> ret = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> tmpMap = new HashMap<>();
            String key = arr[i].name();
            String value = arr[i].getDesc();

            tmpMap.put(key, value);
            ret.put(key, tmpMap);
        }

        return ret;
    }


    public static List toList() {
        PayTypeEnum[] arr = PayTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            map.put("name", arr[i].name());
            list.add(map);
        }
        return list;
    }

    public static PayTypeEnum getEnum(String name) {
        PayTypeEnum[] arr = PayTypeEnum.values();
        PayTypeEnum ret = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equalsIgnoreCase(name)) {
                ret = arr[i];
                break;
            }
        }
        return ret;
    }

    public static List getPayTypeList(String way) {
        PayTypeEnum[] arr = PayTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].paymentPlatform.equals(way)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("desc", arr[i].getDesc());
                map.put("name", arr[i].name());
                list.add(map);
            }
        }
        return list;
    }

    public static String getJsonStr() {
        PayTypeEnum[] arr = PayTypeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (PayTypeEnum senum : arr) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(senum).append("',desc:'")
                    .append(senum.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
