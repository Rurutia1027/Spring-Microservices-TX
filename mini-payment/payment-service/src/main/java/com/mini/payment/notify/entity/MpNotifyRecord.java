package com.mini.payment.notify.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(schema = "notify_record")
public class MpNotifyRecord extends DomainImpl {
}
