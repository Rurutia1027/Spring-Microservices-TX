package com.mini.payment.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum SecurityRatingEnum {

    MD5("MD5"),

    MD5_IP("MD5+IP WhiteList");

    private String desc;

    SecurityRatingEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SecurityRatingEnum getEnum(String enumName) {
        SecurityRatingEnum resultEnum = null;
        SecurityRatingEnum[] arr = SecurityRatingEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        SecurityRatingEnum[] arr = SecurityRatingEnum.values();
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
        SecurityRatingEnum[] arr = SecurityRatingEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", arr[i].name());
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static String getJsonStr() {
        SecurityRatingEnum[] enums = SecurityRatingEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (SecurityRatingEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
