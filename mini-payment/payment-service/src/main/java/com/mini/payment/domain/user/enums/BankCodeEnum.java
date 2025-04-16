package com.mini.payment.domain.user.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BankCodeEnum {

    ICBC("ICBC"),

    CMBCHINA("CMBCHINA"),

    ABC("ABC"),

    CCB("CCB"),

    BCCB("BCCB"),

    BOCO("BOCO"),

    CMBC("CMBC"),

    PINGANBANK("PINGANBANK"),

    CIB("CIB"),

    NJCB("NJCB"),

    CEB("CEB"),

    BOC("BOC"),

    CGB("CGB"),

    SHB("SHB"),

    SPDB("SPDB"),

    POST("POST"),

    CBHB("CBHB"),

    HKBEA("HKBEA"),

    NBCB("NBCB"),

    ECITIC("ECITIC"),

    BJRCB("BJRCB"),

    HXB("HXB"),

    CZ("CZ"),

    HZBANK("HZBANK"),

    SRCB("SRCB"),

    NCBBANK("NCBBANK"),

    SCCB("SCCB"),

    ZJTLCB("ZJTLCB"),

    HKB("HKB"),

    OTHER("OTHER");


    private String desc;

	BankCodeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BankCodeEnum getEnum(String enumName) {
        BankCodeEnum resultEnum = null;
        BankCodeEnum[] arr = BankCodeEnum.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name().equals(enumName)) {
                resultEnum = arr[i];
                break;
            }
        }
        return resultEnum;
    }

    public static Map<String, Map<String, Object>> toMap() {
        BankCodeEnum[] arr = BankCodeEnum.values();
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
        BankCodeEnum[] arr = BankCodeEnum.values();
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
        BankCodeEnum[] enums = BankCodeEnum.values();
        StringBuffer jsonStr = new StringBuffer("[");
        for (BankCodeEnum item : enums) {
            if (!"[".equals(jsonStr.toString())) {
                jsonStr.append(",");
            }
            jsonStr.append("{id:'").append(item).append("',desc:'").append(item.getDesc()).append("'}");
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
