package com.mini.payment.controller;

import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.service.PmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class PmsUserController {
    @Autowired
    private PmsUserService pmsUserService ;
}
