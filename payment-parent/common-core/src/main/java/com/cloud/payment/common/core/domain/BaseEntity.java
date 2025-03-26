package com.cloud.payment.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity extends DomainImpl {
    private Integer version;
    private String status;
    private String creator;
    private Date createTime;
    private String editor;
    private Date editTime;
    private String desc;
}
