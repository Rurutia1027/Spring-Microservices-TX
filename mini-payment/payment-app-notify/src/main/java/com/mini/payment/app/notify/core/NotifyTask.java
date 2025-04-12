package com.mini.payment.app.notify.core;

import com.alibaba.fastjson2.JSON;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.NotifyStrategy;
import com.mini.payment.app.notify.enums.NotifyStatusEnum;
import com.mini.payment.exception.BizException;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Service("notifyTask")
public class NotifyTask implements Runnable, Delayed {
    private final Logger LOG = LoggerFactory.getLogger(NotifyTask.class);

    private long executeTime;
    private MpNotifyRecord notifyRecord;
    private NotifyQueue notifyQueue;
    private NotifyStrategy notifyStrategy;
    private NotifyPersist notifyPersist;

    public NotifyTask() {
    }

    public NotifyTask(MpNotifyRecord record, NotifyQueue notifyQueue,
                      NotifyStrategy notifyStrategy) {
        super();
        this.notifyRecord = record;
        this.notifyQueue = notifyQueue;
        this.notifyStrategy = notifyStrategy;
        this.executeTime = getExecuteTime(notifyRecord);
    }

    private long getExecuteTime(MpNotifyRecord notifyRecord) {
        long lastTime = notifyRecord.getLastNotifyTime().getTime();
        Integer nextNotifyTime =
                notifyStrategy.getNotifyParams().get(notifyRecord.getNotifyTimes());
        return (Objects.isNull(nextNotifyTime) ? 0 : nextNotifyTime + 1000) + lastTime;
    }

    public int compareTo(Delayed o) {
        NotifyTask task = (NotifyTask) o;
        return executeTime > task.executeTime ? 1 : (executeTime < task.executeTime ? -1 : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), unit.SECONDS);
    }

    @Override
    public void run() {
        Integer notifyTimes = notifyRecord.getNotifyTimes();

        // go notify
        try {
            LOG.info("Notify Url: " + notifyRecord.getUrl() + " ;notify id: "
                    + notifyRecord.getId() + ";notify times:" + notifyRecord.getNotifyTimes());

            // todo: implement http client and wrap inner params


            notifyRecord.setNotifyTimes(notifyTimes + 1);
            String successValue = notifyStrategy.getSuccessValue();
            String responseMsg = "";
            Integer responseStatus = null;

            // here we fetch response status and those status determines
            // the record's inner information value
            if (Objects.nonNull(null) && ((responseStatus == 200 || responseStatus == 201 || responseStatus == 202 || responseStatus == 203
                    || responseStatus == 204 || responseStatus == 205 || responseStatus == 206))) {
                // notify success, update the notify record's success status to db
                if (responseMsg.trim().equals(successValue)) {
                    notifyPersist.updateNotifyRecord(String.valueOf(notifyRecord.getId()),
                            notifyRecord.getNotifyTimes(),
                            NotifyStatusEnum.SUCCESS.name());

                } else {
                    notifyQueue.addItemToList(notifyRecord);
                    notifyPersist.updateNotifyRecord(String.valueOf(notifyRecord.getId()),
                            notifyRecord.getNotifyTimes(),
                            NotifyStatusEnum.HTTP_REQUEST_SUCCESS.name());
                }
                LOG.info("Update NotifyRecord: " + JSON.toJSONString(notifyRecord)
                        + "; responseMsg:" + responseMsg);
            } else {
                // notify failed, add the record item to message local list
                // the list will modify record status, and determine whether this record
                // gonna be re-send or append to failed message queue and also update the
                // record's db table status
                notifyQueue.addItemToList(notifyRecord);
                notifyPersist.updateNotifyRecord(String.valueOf(notifyRecord.getId()),
                        notifyRecord.getNotifyTimes(),
                        NotifyStatusEnum.HTTP_REQUEST_FALIED.name());
            }

            // add audit log info to db to record the notify record sending status
            notifyPersist.saveNotifyRecordAuditLog(String.valueOf(notifyRecord.getId()),
                    notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(),
                    notifyRecord.getUrl(), responseMsg, responseStatus);
        } catch (BizException e) {
            LOG.error("NotifyTask got exception:", e);
        } catch (Exception e) {
            LOG.error("NotifyTask got exception:", e);
            notifyQueue.addItemToList(notifyRecord);
            notifyPersist.updateNotifyRecord(String.valueOf(notifyRecord.getId()),
                    notifyRecord.getNotifyTimes(),
                    NotifyStatusEnum.HTTP_REQUEST_FALIED.name());
            notifyPersist.saveNotifyRecordAuditLog(String.valueOf(notifyRecord.getId()),
                    notifyRecord.getMerchantNo(),
                    notifyRecord.getMerchantOrderNo(),
                    notifyRecord.getUrl(), "", HttpStatus.SC_METHOD_FAILURE);
        }
    }
}
