package com.mini.payment.app.polling.core;

import com.mini.payment.domains.notify.entity.MpOrderResultQueryVo;
import com.mini.payment.app.polling.OrderPollingRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class PollingTask implements Runnable, Delayed {
    private static final Logger LOG = LoggerFactory.getLogger(PollingTask.class);

    private long executeTime;
    private MpOrderResultQueryVo mpOrderResultQueryVo;
    private PollingPersist pollingPersist = OrderPollingRunner.cachePollingPersist;

    public PollingTask() {
    }

    public PollingTask(MpOrderResultQueryVo orderQueryVo) {
        super();
        this.mpOrderResultQueryVo = orderQueryVo;
        this.executeTime = getExecuteTime(orderQueryVo);
    }

    private long getExecuteTime(MpOrderResultQueryVo orderQueryVo) {
        return 0;
    }


    // function decides DelayedQueue's inner element sorting order
    @Override
    public int compareTo(Delayed o) {
        PollingTask item = (PollingTask) o;
        return this.executeTime > item.executeTime ? 1
                : (executeTime < item.executeTime ? -1 : 0);
    }

    @Override
    public void run() {
        pollingPersist.getOrderResult(this.mpOrderResultQueryVo);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }
}
