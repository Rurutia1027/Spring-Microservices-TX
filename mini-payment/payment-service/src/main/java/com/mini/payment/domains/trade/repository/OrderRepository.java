package com.mini.payment.domains.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.domains.trade.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order>,
        JpaSpecificationExecutor<Order> {

}