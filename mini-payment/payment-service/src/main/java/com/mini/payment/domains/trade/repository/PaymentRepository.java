package com.mini.payment.domains.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.domains.trade.entity.Payment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends BaseRepository<Payment>,
        JpaSpecificationExecutor<Payment> {

    @Query("SELECT p FROM Payment p WHERE p.bankOrderNo = :bankOrderNo")
    List<Payment> findAllByBankOrderNo(@Param("bankOrderNo") String bankOrderNo);

    @Query("SELECT p FROM Payment  p WHERE p.bankOrderNo = :bankOrderNo")
    Payment findByBankOrderNo(@Param("bankOrderNo") String bankOrderNo);
}