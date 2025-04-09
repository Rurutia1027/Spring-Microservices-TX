package com.mini.payment.permission;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
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

}
