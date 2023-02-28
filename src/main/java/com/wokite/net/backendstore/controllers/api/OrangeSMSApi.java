package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.controllers.MessageModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface OrangeSMSApi {

    @PostMapping(value = APP_ROOT + "/sendSMS")
    @ApiOperation(value = "Envoyer un sms",
            notes = "Cette méthode permet d'envoyer un message à un client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le message a été envoyé")
    })
    void sendSMS(@RequestBody MessageModel messageModel);
}
