package com.mini.payment.permission;

import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsPermission;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.utils.StringUtil;

import java.util.Date;

public class PmsMockUtils {
    public static PmsRole mockPmsRole() {
        PmsRole pmsRole = new PmsRole();
        pmsRole.setRoleCode(StringUtil.get32UUID());
        pmsRole.setRoleName(StringUtil.get36UUID());
        pmsRole.setComment(StringUtil.get36UUID());
        pmsRole.setCreateTime(new Date());
        return pmsRole;
    }

    public static PmsMenu mockPmsMenu() {
        PmsMenu pmsMenu = new PmsMenu();
        pmsMenu.setName(StringUtil.get36UUID());
        pmsMenu.setComment(StringUtil.get36UUID());
        pmsMenu.setIsLeaf(false);
        pmsMenu.setLevel(0L);
        return pmsMenu;
    }

    public static PmsAuditLog mockPmsAuditLog() {
        PmsAuditLog ret = new PmsAuditLog();
        ret.setIp("127.0.0.1");
        ret.setStatus(StringUtil.get32UUID());
        ret.setCreateTime(new Date());
        ret.setContent(StringUtil.get32UUID());
        ret.setOpType(StringUtil.get32UUID());
        return ret;
    }

    public static PmsUser mockPmsUser() {
        PmsUser ret = new PmsUser();
        ret.setEncodedPassword(StringUtil.get32UUID(), null);
        ret.setLoginName(StringUtil.get32UUID());
        ret.setLoginPwd(StringUtil.get32UUID());
        ret.setCreateTime(new Date());
        ret.setSalt(StringUtil.get32UUID());
        return ret;
    }

    public static PmsPermission mockPmsPermission() {
        PmsPermission pmsPermission = new PmsPermission();
        pmsPermission.setPermission(StringUtil.get36UUID());
        pmsPermission.setPermissionName(StringUtil.get32UUID());
        pmsPermission.setCreateTime(new Date());
        return pmsPermission;
    }
}
