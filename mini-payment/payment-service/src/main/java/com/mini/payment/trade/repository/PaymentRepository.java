package com.mini.payment.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.trade.entity.Payment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<Payment>,
        JpaSpecificationExecutor<Payment> {
}
