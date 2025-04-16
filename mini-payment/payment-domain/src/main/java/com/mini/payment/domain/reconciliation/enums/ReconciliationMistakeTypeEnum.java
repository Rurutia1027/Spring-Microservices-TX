package com.mini.payment.domain.reconciliation.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ReconciliationMistakeTypeEnum {

    BANK_MISS("order cannot found on bank records"),
    PLATFORM_MISS("order cannot found on platform records"),
    PLATFORM_SHORT_STATUS_MISMATCH("platform failed, but bank payment success"), //
    PLATFORM_SHORT_CASH_MISMATCH("bank payment amount > platform payment amount"), //
    PLATFORM_OVER_CASH_MISMATCH("bank payment amount < platform payment amount"),
    PLATFORM_OVER_STATUS_MISMATCH("platform success, but bank payment failed"),
    FEE_MISMATCH("fee not match");


    private String desc;

    ReconciliationMistakeTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        ReconciliationMistakeTypeEnum[] arr = ReconciliationMistakeTypeEnum.values();
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
        ReconciliationMistakeTypeEnum[] arr = ReconciliationMistakeTypeEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            map.put("name", arr[i].name());
            list.add(map);
        }
        return list;
    }

    public static ReconciliationMistakeTypeEnum getEnum(String name) {
        ReconciliationMistakeTypeEnum[] arr = ReconciliationMistakeTypeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equalsIgnoreCase(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static String getJsonStr() {
        ReconciliationMistakeTypeEnum[] enums = ReconciliationMistakeTypeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (ReconciliationMistakeTypeEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
