package com.mini.payment.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.trade.entity.Bank;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends BaseRepository<Bank>, JpaSpecificationExecutor<Bank> {
}