package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class OutboundSMSTextMessage implements Serializable {
    private String message;
}
