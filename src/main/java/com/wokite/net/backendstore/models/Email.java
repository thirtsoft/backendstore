package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "email")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Email implements Serializable {

    public static final String from = "thirdiallo@gmail.com";
    private static final String name = "Wokite";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recever", length = 150)
    private String recever;

    @Column(name = "subject", length = 150)
    private String subject;

    @Column(name = "message", length = 250)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "four_id")
    private Fournisseur fournisseur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "actif")
    private int actif;

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getFrom() {
        return from;
    }

    public String getRecever() {
        return recever;
    }

    public void setRecever(String recever) {
        this.recever = recever;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
