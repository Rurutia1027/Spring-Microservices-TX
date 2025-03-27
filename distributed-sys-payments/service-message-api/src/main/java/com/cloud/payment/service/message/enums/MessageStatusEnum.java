package com.cloud.payment.service.message.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MessageStatusEnum {

    WAITING_CONFIRM("To Be Confirmed"),

    SENDING("Sending");
    private String desc;

    private MessageStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        MessageStatusEnum[] arr = MessageStatusEnum.values();
        Map<String, Map<String, Object>> enumMap = new HashMap<>();

        for (int num = 0; num < arr.length; num++) {
            Map<String, Object> map = new HashMap<>();
            String key = arr[num].name();
            String value = arr[num].getDesc();
            map.put("desc", value);
            enumMap.put(key, map);
        }
        return enumMap;
    }

    public static List toList() {
        MessageStatusEnum[] ary = MessageStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < ary.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", ary[i].getDesc());
            map.put("name", ary[i].name());
            list.add(map);
        }
        return list;
    }

    public static MessageStatusEnum getEnum(String name) {
        MessageStatusEnum[] arry = MessageStatusEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String getJsonStr() {
        MessageStatusEnum[] enums = MessageStatusEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (MessageStatusEnum senum : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
