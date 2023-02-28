package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Email;
import com.wokite.net.backendstore.models.Fournisseur;

public interface EmailService {

    void sendEmail(Email email);

    void sendMail(Fournisseur fournisseur);

    void sendMailToAllFournisseurs(Email email);

    void sendMailToClient(Client client);

}
