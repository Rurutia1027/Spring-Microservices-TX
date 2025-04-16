package com.mini.payment.domain.trade.service;

import com.mini.payment.domain.trade.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {
    Page<Merchant> listPage(Merchant param, Pageable pageable);

    Merchant saveData(Merchant merchant);

    Merchant merchantRegister(Merchant merchant);

    Merchant getByBusinessCode(String businessCode);
}