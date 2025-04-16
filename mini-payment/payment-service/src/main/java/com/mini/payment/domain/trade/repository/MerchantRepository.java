package com.mini.payment.domain.trade.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.domain.trade.entity.Merchant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends BaseRepository<Merchant>,
        JpaSpecificationExecutor<Merchant> {

}