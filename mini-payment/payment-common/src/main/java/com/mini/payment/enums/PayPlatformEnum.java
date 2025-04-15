package com.mini.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PayPlatformEnum {

    WEIXIN("Wechat"),

    ALIPAY("Alipay"),

    TEST_PAY("Mock Test Payment"),

    TEST_PAY_HTTP_CLIENT("Mock Http Client Payment");

    /**
     * 描述
     */
    private String desc;

    private PayPlatformEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        PayPlatformEnum[] ary = PayPlatformEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < ary.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = ary[num].name();
            map.put("desc", ary[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        PayPlatformEnum[] ary = PayPlatformEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }

    public static PayPlatformEnum getEnum(String name) {
        PayPlatformEnum[] arry = PayPlatformEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

    public static String getJsonStr() {
        PayPlatformEnum[] arr = PayPlatformEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (PayPlatformEnum item : arr) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'")
                    .append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
