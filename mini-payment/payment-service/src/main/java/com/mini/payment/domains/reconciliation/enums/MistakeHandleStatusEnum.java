package com.mini.payment.domains.reconciliation.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MistakeHandleStatusEnum {

    HANDLED("handled"),

    UN_HANDLED("un-handled");

    private String desc;

    MistakeHandleStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static MistakeHandleStatusEnum getEnum(String name) {
        MistakeHandleStatusEnum[] arr = MistakeHandleStatusEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static List toList() {
        MistakeHandleStatusEnum[] arr = MistakeHandleStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            map.put("name", arr[i].name());
            list.add(map);
        }
        return list;
    }

}
