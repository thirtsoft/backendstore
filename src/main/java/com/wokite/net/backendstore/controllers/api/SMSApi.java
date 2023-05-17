package com.wokite.net.backendstore.controllers.api;


import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Fournisseur;
import com.wokite.net.backendstore.models.SubCategory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface SMSApi {
/*
    @PostMapping(value = APP_ROOT + "/sendSMSToFournisseur")
    @ApiOperation(value = "Envoyer un sms au Fournisseur",
            notes = "Cette méthode permet d'envoyer un sms au Fournisseur")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "SMS envoyé avec succès"),
            @ApiResponse(code = 400, message = "Aucun SMS envoyé")
    })
    Message sendSMS(@RequestBody Fournisseur fournisseur);

    @PostMapping(value = APP_ROOT + "/sendSMSToCustomer")
    @ApiOperation(value = "Envoyer un sms au Client",
            notes = "Cette méthode permet d'envoyer un sms au Client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "SMS envoyé avec succès"),
            @ApiResponse(code = 400, message = "Aucun SMS envoyé")
    })
    Message sendSMSToCustomer(@RequestBody Client client);

 */

}
