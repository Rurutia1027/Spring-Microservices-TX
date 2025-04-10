package com.mini.payment.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Admin!";
    }

    @PreAuthorize("hasPermission('menu', 'query')")
    @GetMapping("/sayHi")
    public String sayHi() {
        return "Say Hi, Admin!";
    }
}