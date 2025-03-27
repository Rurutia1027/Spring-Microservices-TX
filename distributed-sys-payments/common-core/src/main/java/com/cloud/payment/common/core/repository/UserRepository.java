package com.cloud.payment.common.core.repository;

import com.cloud.payment.common.core.domain.User;
import com.cloud.payment.common.core.persistence.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    User findByName(String name);
}
