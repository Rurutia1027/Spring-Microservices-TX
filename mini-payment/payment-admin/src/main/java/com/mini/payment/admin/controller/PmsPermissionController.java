package com.mini.payment.admin.controller;

import com.mini.payment.permission.service.PmsPermissionService;
import com.mini.payment.permission.service.PmsRolePermissionService;
import com.mini.payment.permission.service.PmsUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PmsPermissionController {
    @Autowired
    private PmsPermissionService pmsPermissionService;
    @Autowired
    private PmsUserPermissionService pmsUserPermissionService;
    @Autowired
    private PmsRolePermissionService pmsRolePermissionService;


}
