package com.mini.payment.controller;

import com.mini.payment.permission.service.PmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class PmsRoleController {
    @Autowired
    private PmsRoleService pmsRoleService;
}
