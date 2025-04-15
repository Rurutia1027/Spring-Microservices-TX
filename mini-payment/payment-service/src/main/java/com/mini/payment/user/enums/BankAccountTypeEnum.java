package com.mini.payment.user.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum BankAccountTypeEnum {

    PUBLIC_ACCOUNT("public account"),
    PRIVATE_DEBIT_ACCOUNT("private debit account");

    private String desc;

    BankAccountTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        BankAccountTypeEnum[] arr = BankAccountTypeEnum.values();
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

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List toList() {
        BankAccountTypeEnum[] arr = BankAccountTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            map.put("name", arr[i].name());
            list.add(map);
        }
        return list;
    }


    public static BankAccountTypeEnum getEnum(String enumName) {
        BankAccountTypeEnum resultEnum = null;
        BankAccountTypeEnum[] arr = BankAccountTypeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }
}
