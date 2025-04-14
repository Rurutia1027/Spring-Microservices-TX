/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mini.payment.trade.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TradeStatusEnum {

    SUCCESS("transaction success"),

    FAILED("transaction fail"),

    CREATED("order created"),

    CANCELED("order cancel"),

    WAITING_PAYMENT("pending on payment");


    private String desc;

    TradeStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<String, Map<String, Object>> toMap() {
        TradeStatusEnum[] arr = TradeStatusEnum.values();
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
        TradeStatusEnum[] arr = TradeStatusEnum.values();
        List list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("desc", arr[i].getDesc());
            map.put("name", arr[i].name());
            list.add(map);
        }
        return list;
    }

    public static TradeStatusEnum getEnum(String name) {
        TradeStatusEnum[] arr = TradeStatusEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equalsIgnoreCase(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static String getJsonStr() {
        TradeStatusEnum[] enums = TradeStatusEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (TradeStatusEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
