package com.mini.payment.mock;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MockMessage implements Serializable, Delayed {
    String messageType; // merchant, order, trade
    String msgUUID;
    long timestamp;
    long delayUntil;

    public MockMessage() {
    }

    public MockMessage(String messageType, String msgUUID) {
        this.messageType = messageType;
        this.msgUUID = msgUUID;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMsgUUID() {
        return msgUUID;
    }

    public void setMsgUUID(String msgUUID) {
        this.msgUUID = msgUUID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDelayUntil(long delayUntil, TimeUnit unit) {
        this.delayUntil = System.currentTimeMillis() + unit.toMillis(delayUntil);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = delayUntil - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MICROSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof MockMessage) {
            return Long.compare(this.delayUntil, ((MockMessage) o).delayUntil);
        }
        return 0;
    }
}
