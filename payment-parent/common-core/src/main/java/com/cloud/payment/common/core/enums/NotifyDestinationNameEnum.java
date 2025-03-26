package com.cloud.payment.common.core.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NotifyDestinationNameEnum {
    // Accounting Queue's Notification Type
    ACCOUNTING_NOTIFY("accounting queue"),

    // Bank Queue's Notification Type
    BANK_NOTIFY("bank queue"),

    // Merchant Queue's Notification Type
    MERCHANT_NOTIFY("merchant queue");

    private String desc;

    private NotifyDestinationNameEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static NotifyDestinationNameEnum getEnum(String enumName) {
        NotifyDestinationNameEnum[] arr = NotifyDestinationNameEnum.values();
        NotifyDestinationNameEnum ret = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                ret = arr[i];
                break;
            }
        }
        return ret;
    }

    public static Map<String, Map<String, Object>> toMap() {
        NotifyDestinationNameEnum[] arr = NotifyDestinationNameEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < arr.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = arr[num].name();
            map.put("desc", arr[num].getDesc());
            map.put("name", arr[num].name());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        NotifyDestinationNameEnum[] ary = NotifyDestinationNameEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }
}
