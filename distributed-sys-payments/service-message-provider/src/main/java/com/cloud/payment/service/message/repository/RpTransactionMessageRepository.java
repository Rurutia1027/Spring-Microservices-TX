package com.cloud.payment.service.message.repository;

import com.cloud.payment.common.core.persistence.BaseRepository;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface RpTransactionMessageRepository extends BaseRepository<RpTransactionMessage> {

}
