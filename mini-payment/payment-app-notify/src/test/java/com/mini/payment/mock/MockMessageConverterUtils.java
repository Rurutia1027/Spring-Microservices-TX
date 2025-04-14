package com.mini.payment.mock;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

public class MockMessageConverterUtils {

    // Object → JSONObject
    public static JSONObject toJson(MockMessage mockMessage) {
        if (mockMessage == null) return new JSONObject();
        return (JSONObject) JSON.toJSON(mockMessage);
    }

    // Object → JSON String
    public static String toJsonString(MockMessage mockMessage) {
        if (mockMessage == null) return "{}";
        return JSON.toJSONString(mockMessage);
    }

    // JSONObject → Object
    public static MockMessage toJavaObj(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isEmpty()) return null;
        return jsonObject.toJavaObject(MockMessage.class);
    }

    // String → Object
    public static MockMessage toJavaObj(String jsonStr) {
        if (jsonStr == null || jsonStr.isBlank()) return null;
        return JSON.parseObject(jsonStr, MockMessage.class);
    }
}