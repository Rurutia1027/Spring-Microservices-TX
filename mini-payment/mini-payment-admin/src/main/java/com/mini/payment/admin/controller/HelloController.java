package com.mini.payment.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @GetMapping("/testAdminAuthority")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String testAdminAuthority() {
        return "Say Hi, Admin!";
    }

    @GetMapping("/testUserAuthority")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testUserAuthority() {
        return "Say Hi, User!";
    }

    @GetMapping("/testNoAuthority")
    public String testNoAuthority() {
        return "hello nobody";
    }
}



