package com.cloud.payment.common.core.persistence;

import com.cloud.payment.common.core.domain.DomainImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}
