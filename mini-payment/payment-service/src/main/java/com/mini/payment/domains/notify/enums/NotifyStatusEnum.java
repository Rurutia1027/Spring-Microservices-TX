package com.mini.payment.domains.notify.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NotifyStatusEnum {

    CREATED("notify record already created¬"),
    SUCCESS("notify success"),
    FAILED("notify failure"),
    HTTP_REQUEST_SUCCESS("http request success"),
    HTTP_REQUEST_FALIED("http request failure");


    private String desc;

    NotifyStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        NotifyStatusEnum[] arr = NotifyStatusEnum.values();
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
        NotifyStatusEnum[] arr = NotifyStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            list.add(map);
        }
        return list;
    }

    public static NotifyStatusEnum getEnum(String name) {
        NotifyStatusEnum[] arr = NotifyStatusEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equalsIgnoreCase(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static String getJsonStr() {
        NotifyStatusEnum[] enums = NotifyStatusEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (NotifyStatusEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'")
                    .append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
