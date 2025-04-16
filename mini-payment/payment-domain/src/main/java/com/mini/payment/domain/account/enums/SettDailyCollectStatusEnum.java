package com.mini.payment.domain.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SettDailyCollectStatusEnum {

    /**
     * settled
     */
    SETTLED("settled"),

    /**
     * un-settled
     */
    UNSETTLED("un-settled");

    private String desc;

    SettDailyCollectStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SettDailyCollectStatusEnum getEnum(String enumName) {
        SettDailyCollectStatusEnum resultEnum = null;
        SettDailyCollectStatusEnum[] arr = SettDailyCollectStatusEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        SettDailyCollectStatusEnum[] arr = SettDailyCollectStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < arr.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = String.valueOf(getEnum(arr[num].name()));
            map.put("value", String.valueOf(arr[num].name()));
            map.put("desc", arr[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        SettDailyCollectStatusEnum[] arr = SettDailyCollectStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("value", String.valueOf(arr[i].name()));
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
