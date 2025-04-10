package com.mini.payment.permission.repository;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PmsMenuRepository extends BaseRepository<PmsMenu>,
        JpaSpecificationExecutor<PmsMenu> {

    @Query("SELECT m FROM PmsMenu m WHERE m.name IN :names")
    Set<PmsMenu> listAllByNames(@Param("names") Set<String> menuNames);

    @Query("SELECT m FROM PmsMenu m WHERE m.id IN :ids")
    Set<PmsMenu> listAllByIds(@Param("ids") Set<Long> menuIds);

    @Query("SELECT m FROM PmsMenu m WHERE m.parent.id = :pid")
    List<PmsMenu> listByParentId(@Param("pid") Long parentId);

    @Query("SELECT m FROM PmsMenu m WHERE m.name = :name AND m.isLeaf = true")
    List<PmsMenu> findByNameAndLeaf(@Param("name") String menuName);
}
