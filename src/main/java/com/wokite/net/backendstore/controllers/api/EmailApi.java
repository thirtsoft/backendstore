package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Email;
import com.wokite.net.backendstore.models.Fournisseur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface EmailApi {

    @PostMapping(value = APP_ROOT + "/mails/sendEmail")
    @ApiOperation(value = "Envoyer un email",
            notes = "Cette méthode permet d'envoyer un email",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Email> sendEmail(@RequestBody Email email);

    @PostMapping(value = APP_ROOT + "/mails/sendMailToFournisseur")
    @ApiOperation(value = "Envoyer un email à un Fournisseurs",
            notes = "Cette méthode permet d'envoyer un email à un Fournisseurs",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Fournisseur> sendMailToFournisseur(@RequestBody Fournisseur fournisseur);

    @PostMapping(value = APP_ROOT + "/mails/sendMailToAllFournisseur")
    @ApiOperation(value = "Envoyer un email à plusieurs Fournisseurs",
            notes = "Cette méthode permet d'envoyer un email à plusieurs Fournisseurs",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Email> sendMailToAllFournisseur(@RequestBody Email email);

    @PostMapping(value = APP_ROOT + "/mails/sendMailToCustomer")
    @ApiOperation(value = "Envoyer un email à un Client",
            notes = "Cette méthode permet d'envoyer un email à un Client",
            response = Email.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'email a été envoyé / modifié"),
            @ApiResponse(code = 400, message = "Aucun Email  envoyé")
    })
    ResponseEntity<Client> sendMailToCustomer(@RequestBody Client client);


}
