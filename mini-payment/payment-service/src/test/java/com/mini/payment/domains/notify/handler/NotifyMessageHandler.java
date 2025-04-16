package com.mini.payment.domains.notify.handler;

import com.alibaba.fastjson2.JSONObject;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotifyMessageHandler {
    // key: queue-name, value: queue consumed message in json obj
    private Map<String, List<JSONObject>> cache;

    public void addToCache(String queue, JSONObject jsonObj) {
        cache.putIfAbsent(queue, Lists.list(jsonObj));
    }

    public List<JSONObject> getFromCache(String queue) {
        return cache.getOrDefault(queue, Lists.newArrayList());
    }

    public NotifyMessageHandler() {
        this.cache = new HashMap<>();
    }

    public NotifyMessageHandler(Map<String, List<JSONObject>> cache) {
        this.cache = cache;
    }
}
