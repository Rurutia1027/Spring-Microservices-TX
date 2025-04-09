package com.mini.payment.permission.service.impl;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.permission.service.PmsAuditLogService;
import com.mini.payment.utils.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsAuditLogServiceImplTest {
    @Autowired
    private PmsAuditLogService pmsAuditLogService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsAuditLogService);
    }

    @Test
    public void testGetById() {
        PmsAuditLog pmsAuditLog = PmsMockUtils.mockPmsAuditLog();
        // save and then query
        PmsAuditLog pmsAuditLogRet = pmsAuditLogService.saveData(pmsAuditLog);
        Assertions.assertTrue(pmsAuditLogRet.getId() > 0);
        PmsAuditLog queryItem = pmsAuditLogService.getById(pmsAuditLogRet.getId());
        Assertions.assertEquals(queryItem.getId(), pmsAuditLogRet.getId());
    }

    @Test
    public void testUpdateData() {
        PmsAuditLog pmsAuditLog = PmsMockUtils.mockPmsAuditLog();
        // save, query and update
        PmsAuditLog pmsAuditLogRet = pmsAuditLogService.saveData(pmsAuditLog);
        Assertions.assertTrue(pmsAuditLogRet.getId() > 0);
        String uuid = StringUtil.get36UUID();
        pmsAuditLogRet.setContent(uuid);
        PmsAuditLog queryRet = pmsAuditLogService.updateData(pmsAuditLogRet);
        Assertions.assertEquals(queryRet.getContent(), uuid);
    }

    @Test
    public void testListPage() {
        // mock data sets
        List<PmsAuditLog> pmsAuditLogList = new ArrayList<>();
        String queryTag = StringUtil.get32UUID();
        String opType = "write";
        for (int i = 0; i < 100; i++) {
            PmsAuditLog pmsAuditLog = PmsMockUtils.mockPmsAuditLog();
            pmsAuditLog.setOpType(opType);
            pmsAuditLog.setContent(queryTag);
            PmsAuditLog ret = pmsAuditLogService.saveData(pmsAuditLog);
            pmsAuditLogList.add(ret);
        }
        Assertions.assertTrue(pmsAuditLogList.size() > 0);
        // query via list page via multiple params
        PmsAuditLog param = new PmsAuditLog();
        param.setContent(queryTag);
        param.setOpType(opType);

        int expectedPageSize = 10;
        PageRequest pageable = PageRequest.of(2, 10, Sort.by(Sort.Direction.DESC, "id"));

        Page<PmsAuditLog> ret = pmsAuditLogService.listPage(param, pageable);
        Assertions.assertNotNull(ret);
        Assertions.assertTrue(ret.getSize() > 0);
        Assertions.assertEquals(ret.getSize(), expectedPageSize);
    }
}