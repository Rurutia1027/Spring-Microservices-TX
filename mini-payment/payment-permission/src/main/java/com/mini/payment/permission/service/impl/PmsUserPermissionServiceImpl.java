package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.repository.PmsUserPermissionRepository;
import com.mini.payment.permission.service.PmsUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pmsUserPermissionService")
public class PmsUserPermissionServiceImpl implements PmsUserPermissionService {
    @Autowired
    private PmsUserPermissionRepository pmsUserPermissionRepository;

}
