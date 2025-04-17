package com.mini.payment.domain.persistence;

import java.util.List;

public interface QueryPostProcessor {
    List processListResult(List result);
}
