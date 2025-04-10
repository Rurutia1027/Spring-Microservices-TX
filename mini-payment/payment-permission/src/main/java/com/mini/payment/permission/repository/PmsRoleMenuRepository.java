package com.mini.payment.permission.repository;

import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PmsRoleMenuRepository extends BaseRepository<PmsRoleMenu>,
        JpaSpecificationExecutor<PmsRoleMenu> {
    @Query("SELECT COUNT (rm) FROM PmsRoleMenu rm WHERE rm.role.id = :roleId")
    int countByRoleId(@Param("roleId") Long roleId);

    @Modifying
    @Query("DELETE FROM PmsRoleMenu rm WHERE rm.role.id = :roleId")
    void deleteByRoleId(@Param("roleId") Long roleId);

    @Query("SELECT rm.id FROM PmsRoleMenu rm WHERE rm.role.id = :roleId")
    Set<Long> findAllIdsByRoleId(@Param("roleId") Long roleId);
}
