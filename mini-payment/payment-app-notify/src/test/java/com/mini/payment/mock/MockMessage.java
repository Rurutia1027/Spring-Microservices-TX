package com.mini.payment.mock;

import java.io.Serializable;

public class MockMessage implements Serializable {
    String messageType; // merchant, order, trade
    String msgUUID;
    long timestamp;

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
}
