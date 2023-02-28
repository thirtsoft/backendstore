package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.utils.SendSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrangeSMSServiceImpl /* implements OrangeSMSService */ {

    @Autowired
    private SendSMS sendSMS;

    /*
    @Override
    public void sendSMS(String receiver, String message) {
        sendSMS.sendSMS(receiver, message);
    }
    */
}
