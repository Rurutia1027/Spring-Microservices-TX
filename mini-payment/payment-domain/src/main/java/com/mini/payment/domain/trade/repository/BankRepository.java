package com.mini.payment.domain.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.domain.trade.entity.Bank;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends BaseRepository<Bank>, JpaSpecificationExecutor<Bank> {

}