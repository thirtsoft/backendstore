package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.EmailApi;
import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Email;
import com.wokite.net.backendstore.models.Fournisseur;
import com.wokite.net.backendstore.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EmailController implements EmailApi {

    @Autowired private EmailService emailService;

    @Override
    public ResponseEntity<Email> sendEmail(Email email) {
        try {
            emailService.sendEmail(email);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Fournisseur> sendMailToFournisseur(Fournisseur fournisseur) {
        try {
            emailService.sendMail(fournisseur);
            return new ResponseEntity<>(fournisseur, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Email> sendMailToAllFournisseur(Email email) {
        try {
            emailService.sendMailToAllFournisseurs(email);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Client> sendMailToCustomer(Client client) {
        try {
            emailService.sendMailToClient(client);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
