package com.mini.payment.domains.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mini.payment.domains.notify.entity.MpNotifyRecord;
import com.mini.payment.domains.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.domains.notify.enums.NotifyStatusEnum;
import com.mini.payment.domains.notify.enums.NotifyTypeEnum;
import com.mini.payment.domains.notify.repository.MpNotifyRecordAuditLogRepository;
import com.mini.payment.domains.notify.repository.MpNotifyRecordRepository;
import com.mini.payment.domains.notify.service.MpNotifyRecordService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("mpNotifyRecordService")
public class MpNotifyRecordServiceImpl implements MpNotifyRecordService {
    @Autowired
    @Qualifier("merchantNotifyJmsTemplate")
    private JmsTemplate merchantJmsTemplate;
    @Autowired
    @Qualifier("orderNotifyJmsTemplate")
    private JmsTemplate orderJmsTemplate;

    @Autowired
    private MpNotifyRecordRepository mpNotifyRecordRepository;

    @Autowired
    private MpNotifyRecordAuditLogRepository mpNotifyRecordAuditLogRepository;


    @Override
    public void merchantNotifySend(String notifyUrl, String merchantNo, String merchantOrderNo) {
        MpNotifyRecord mpNotifyRecord = new MpNotifyRecord();
        mpNotifyRecord.setNotifyTimes(0);
        mpNotifyRecord.setLimitNotifyTimes(5);
        mpNotifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
        mpNotifyRecord.setUrl(notifyUrl);
        mpNotifyRecord.setMerchantNo(merchantNo);
        mpNotifyRecord.setMerchantOrderNo(merchantOrderNo);
        mpNotifyRecord.setNotifyType(NotifyTypeEnum.MERCHANT.name());

        Object toJson = JSONObject.toJSON(mpNotifyRecord);
        final String msgStr = toJson.toString();
        merchantJmsTemplate.send(session -> session.createTextMessage(msgStr));
    }

    @Override
    public void orderNotifySend(String bankOrderNo) {
        final String orderNo = bankOrderNo;
        orderJmsTemplate.send(session -> session.createTextMessage(orderNo));
    }

    @Override
    public MpNotifyRecord getNotifyRecordById(Long id) {
        return mpNotifyRecordRepository
                .findById(id).orElse(null);
    }

    @Override
    public MpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(String merchantNo,
                                                                               String merchantOrderNo,
                                                                               String notifyTyp) {
        Specification<MpNotifyRecord> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(merchantNo)) {
                predicates.add(cb.equal(root.get("merchantNo"), merchantNo));
            }

            if (StringUtils.hasText(merchantOrderNo)) {
                predicates.add(cb.equal(root.get("merchantOrderNo"), merchantOrderNo));
            }

            if (StringUtils.hasText(notifyTyp)) {
                predicates.add(cb.equal(root.get("notifyType"), notifyTyp));
            }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return mpNotifyRecordRepository.findOne(spec).orElse(null);
    }

    @Override
    public Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MpNotifyRecord> loadNotifyRecordsFromDB(List<String> notifyStatusList,
                                                        List<Integer> notifyTimeList
            , Pageable pageable) {
        Specification<MpNotifyRecord> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!CollectionUtils.isEmpty(notifyStatusList)) {
                predicates.add(root.get("status").in(notifyStatusList));
            }

            if (!CollectionUtils.isEmpty(notifyTimeList)) {
                predicates.add(root.get("notifyTimes").in(notifyTimeList));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return mpNotifyRecordRepository.findAll(spec, pageable);
    }

    @Override
    public MpNotifyRecord createNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return mpNotifyRecordRepository.save(mpNotifyRecord);
    }

    @Override
    public MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return mpNotifyRecordRepository.save(mpNotifyRecord);
    }

    @Override
    public MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog) {
        return mpNotifyRecordAuditLogRepository.save(auditLog);
    }
}
