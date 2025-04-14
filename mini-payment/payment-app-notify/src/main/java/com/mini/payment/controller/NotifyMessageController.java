package com.mini.payment.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotifyMessageController {
    @GetMapping("/test")
    public String testNotifyEndpoint() {
        return "Say Hi, Notify App!";
    }
}
