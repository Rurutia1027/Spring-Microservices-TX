package com.mini.payment.domain.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SettDailyCollectTypeEnum {

    ALL("Deposit/reduce summary"),

    /**
     * temporary summary
     */
    TEMP("temporary summary"),

	/**
     * remained un-summarized
     */
    LEAVE("remained un-summarized");

    private String desc;

    SettDailyCollectTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SettDailyCollectTypeEnum getEnum(String enumName) {
        SettDailyCollectTypeEnum resultEnum = null;
        SettDailyCollectTypeEnum[] arr = SettDailyCollectTypeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        SettDailyCollectTypeEnum[] arr = SettDailyCollectTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < arr.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = arr[num].name();
            map.put("desc", arr[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        SettDailyCollectTypeEnum[] arr = SettDailyCollectTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }
}
