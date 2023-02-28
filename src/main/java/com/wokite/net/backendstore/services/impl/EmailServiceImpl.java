package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.models.Client;
import com.wokite.net.backendstore.models.Email;
import com.wokite.net.backendstore.models.Fournisseur;
import com.wokite.net.backendstore.repository.EmailRepository;
import com.wokite.net.backendstore.services.EmailService;
import com.wokite.net.backendstore.services.FournisseurService;
import com.wokite.net.backendstore.utils.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

    @Autowired private EmailRepository emailRepository;

    @Autowired
    private FournisseurService fournisseurService;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(Email email) throws MailException {
        boolean f = false;
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + email.getName()).append(System.lineSeparator());
        sb.append("\n Message: " + email.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(email.getFournisseur().getEmail());
        mail.setFrom(email.from);
        mail.setSubject(email.getSubject());
        mail.setText(email.getMessage());

        javaMailSender.send(mail);

        emailRepository.save(email);

    }

    @Override
    public void sendMail(Fournisseur fournisseur) throws MailException {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + fournisseur.getSubject());
        sb.append("\n Message : " + fournisseur.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(fournisseur.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(fournisseur.getSubject());
        mail.setText(fournisseur.getMessage());

        javaMailSender.send(mail);

    }

    @Override
    public void sendMailToAllFournisseurs(Email email) throws MailException  {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());

        List<Fournisseur> furnishesList = fournisseurService.findAllFournisseurs();

        SimpleMailMessage mail = new SimpleMailMessage();

        for (int i = 0; i < furnishesList.size(); i++) {
            Fournisseur fournisseur = furnishesList.get(i);
            mail.setTo(fournisseur.getEmail());
            mail.setSubject(email.getSubject());
            mail.setText(email.getMessage());
            mail.setFrom(EmailConstants.from);
        }

        javaMailSender.send(mail);
    }

    @Override
    public void sendMailToClient(Client client) throws MailException  {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : " + EmailConstants.managerName).append(System.lineSeparator());
        sb.append("\n Subject : " + client.getSubject());
        sb.append("\n Message : " + client.getMessage());

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(client.getEmail());
        mail.setFrom(EmailConstants.from);
        mail.setSubject(client.getSubject());
        mail.setText(client.getMessage());

        javaMailSender.send(mail);
    }
}
