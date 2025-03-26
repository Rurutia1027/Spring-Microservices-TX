package com.cloud.payment.common.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends DomainImpl {
    private String name;
    @Version
    private int version;
}
