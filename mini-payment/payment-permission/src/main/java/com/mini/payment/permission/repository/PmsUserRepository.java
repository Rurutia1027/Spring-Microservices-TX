package com.mini.payment.permission.repository;

import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsUserRepository extends BaseRepository<PmsUser>,
        JpaSpecificationExecutor<PmsUser> {

}
