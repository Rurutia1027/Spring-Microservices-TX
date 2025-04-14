package com.mini.payment.trade.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum OrderFromEnum {

    USER_EXPENSE("user exponse");


    private String desc;

    OrderFromEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        OrderFromEnum[] arr = OrderFromEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String key = arr[i].name();
            map.put("desc", arr[i].getDesc());
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        OrderFromEnum[] arr = OrderFromEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static OrderFromEnum getEnum(String name) {
        OrderFromEnum[] arr = OrderFromEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equalsIgnoreCase(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static String getJsonStr() {
        OrderFromEnum[] enums = OrderFromEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (OrderFromEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}