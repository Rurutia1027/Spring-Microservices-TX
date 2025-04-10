package com.mini.payment.permission;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsPermission;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static PmsUser mockPmsUser() {
        PmsUser ret = new PmsUser();
        ret.setLoginName(StringUtil.get32UUID());
        ret.setLoginPwd(StringUtil.get32UUID());
        ret.setCreateTime(new Date());
        ret.setSalt(StringUtil.get32UUID());
        return ret;
    }

    public static PmsPermission mockPmsPermission() {
        PmsPermission pmsPermission = new PmsPermission();
        pmsPermission.setPermission(StringUtil.get36UUID());
        pmsPermission.setResource(StringUtil.get32UUID());
        pmsPermission.setCreateTime(new Date());
        return pmsPermission;
    }

    public static List<PmsMenu> mockPmsMenus(int len) {
        List<PmsMenu> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ret.add(mockPmsMenu());
        }
        return ret;
    }
}
