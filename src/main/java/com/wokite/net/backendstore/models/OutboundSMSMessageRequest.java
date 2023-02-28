package com.wokite.net.backendstore.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class OutboundSMSMessageRequest implements Serializable {
    public String address;
    public String senderAddress;
    public OutboundSMSTextMessage outboundSMSTextMessage;

}
