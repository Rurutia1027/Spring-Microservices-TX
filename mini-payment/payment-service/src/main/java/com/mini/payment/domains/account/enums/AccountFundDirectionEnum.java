package com.mini.payment.domains.account.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AccountFundDirectionEnum {

    /**
     * Add Fund
     */
    ADD("Add Fund"),

    /**
     * Subtract Fund
     */
    SUB("Subtract Fund");

    private String label;

    AccountFundDirectionEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        AccountFundDirectionEnum[] arr = AccountFundDirectionEnum.values();
        for (AccountFundDirectionEnum e : arr) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("label", e.getLabel());
            map.put("name", e.name());
            list.add(map);
        }
        return list;
    }

    public static AccountFundDirectionEnum getEnum(String name) {
        AccountFundDirectionEnum resultEnum = null;
        AccountFundDirectionEnum[] arr = AccountFundDirectionEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(name)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

}
