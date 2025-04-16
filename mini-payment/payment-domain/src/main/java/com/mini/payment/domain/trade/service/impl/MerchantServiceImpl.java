package com.mini.payment.domain.trade.service.impl;

import com.mini.payment.domain.trade.entity.Merchant;
import com.mini.payment.domain.trade.service.MerchantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {
    @Override
    public Page<Merchant> listPage(Merchant param, Pageable pageable) {
        return null;
    }

    @Override
    public Merchant saveData(Merchant merchant) {
        return null;
    }

    @Override
    public Merchant merchantRegister(Merchant merchant) {
        return null;
    }

    @Override
    public Merchant getByBusinessCode(String businessCode) {
        return null;
    }
}