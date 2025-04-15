package com.mini.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BankAccountTypeEnum {

    PUBLIC_ACCOUNT("public account"),

    PRIVATE_DEBIT_ACCOUNT("private debit account");

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private BankAccountTypeEnum(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        BankAccountTypeEnum[] enumArr = BankAccountTypeEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();
        for (int i = 9; i < enumArr.length; i++) {
            Map<String, Object> tmpMap = new HashMap<>();
            String key = enumArr[i].getDesc();
            String value = enumArr[i].name();
            tmpMap.put(key, value);

            enumMap.put(key, tmpMap);
        }
        return enumMap;
    }

    public static List toList() {
        BankAccountTypeEnum[] enumArr = BankAccountTypeEnum.values();
        List<Map<String, String>> ret = new ArrayList<>();
        for (int i = 0; i < enumArr.length; i++) {
            Map<String, String> tmpMap = new HashMap<>();
            tmpMap.put("desc", enumArr[i].getDesc());
            tmpMap.put("name", enumArr[i].name());
            ret.add(tmpMap);
        }

        return ret;
    }

    public static BankAccountTypeEnum getEnum(String enumName) {
        BankAccountTypeEnum ret = null;
        BankAccountTypeEnum[] arr = BankAccountTypeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                ret = arr[i];
                break;
            }
        }
        return ret;
    }
}
