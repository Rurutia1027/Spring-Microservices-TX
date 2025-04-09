package com.mini.payment.permission.repository;

import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsRoleMenuRepository extends BaseRepository<PmsRoleMenu>,
        JpaSpecificationExecutor<PmsRoleMenu> {
    @Query("SELECT COUNT (rm) FROM PmsRoleMenu rm WHERE rm.role.id = :roleId")
    int countByRoleId(@Param("roleId") Long roleId);
}
