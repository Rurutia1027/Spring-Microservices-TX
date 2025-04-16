package com.mini.payment.domains.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AccountTypeEnum {

    /**
     * user
     */
    USER("user"),
    /**
     * enterprise
     */
    ENTERPRISE("enterprise");

    private String desc;

    AccountTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static AccountTypeEnum getEnum(String enumName) {
        AccountTypeEnum resultEnum = null;
        AccountTypeEnum[] arr = AccountTypeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        AccountTypeEnum[] arr = AccountTypeEnum.values();
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
        AccountTypeEnum[] arr = AccountTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }

}
