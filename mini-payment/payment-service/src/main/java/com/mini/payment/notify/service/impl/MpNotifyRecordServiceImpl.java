package com.mini.payment.notify.service.impl;

import com.mini.payment.notify.repository.MpNotifyRecordRespository;
import com.mini.payment.notify.service.MpNotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("mpNotifyRecordService")
public class MpNotifyRecordServiceImpl implements MpNotifyRecordService {
    @Autowired
    private MpNotifyRecordRespository mpNotifyRecordRespository;
}
