package com.mini.payment.domain.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SettRecordStatusEnum {

    /**
     * waiting confirm
     */
    WAIT_CONFIRM("waiting on confirm"),

    /**
     * already audit
     */
    CONFIRMED("already audit"),

    /**
     * audit denied
     */
    CANCEL("audit denied"),

    /**
     * remitting
     */
    REMITTING("remitting"),

    /**
     * remit success
     */
    REMIT_SUCCESS("remit success"),

    /**
     * remit fail
     */
    REMIT_FAIL("remit fail");

    private String desc;

    SettRecordStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SettRecordStatusEnum getEnum(String enumName) {
        SettRecordStatusEnum resultEnum = null;
        SettRecordStatusEnum[] arr = SettRecordStatusEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        SettRecordStatusEnum[] arr = SettRecordStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int num = 0; num < arr.length; num++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = arr[num].name();
            map.put("desc", arr[num].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        SettRecordStatusEnum[] arr = SettRecordStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static boolean checkConfirm(String enumName) {
        SettRecordStatusEnum[] arr = {SettRecordStatusEnum.CANCEL, SettRecordStatusEnum.CONFIRMED};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRemit(String enumName) {
        SettRecordStatusEnum[] arr = {SettRecordStatusEnum.REMIT_FAIL, SettRecordStatusEnum.REMIT_SUCCESS};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                return true;
            }
        }
        return false;
    }
}
