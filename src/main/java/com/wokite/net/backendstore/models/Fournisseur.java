package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "fournisseur")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Fournisseur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", length = 90, unique = true)
    private String code;

    @NotNull
    @Column(name = "raisonSocial", length = 155)
    private String raisonSocial;

    @Column(name = "numeroCompte", length = 100, unique = true)
    private String numeroCompte;

    @NotNull
    @Column(name = "address", length = 210)
    private String address;

    @Column(name = "telephone", length = 30, unique = true)
    private String telephone;

    @NotNull
    @Column(name = "mobile", length = 30, unique = true)
    private String mobile;

    @Column(name = "fax", length = 50)
    private String fax;

    @Email
    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "subject", length = 100)
    private String subject;

    @Column(name = "message", length = 250)
    private String message;

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

    public Fournisseur(Long id, String raisonSocial, String numeroCompte, String mobile, String email) {
        this.id = id;
        this.raisonSocial = raisonSocial;
        this.numeroCompte = numeroCompte;
        this.mobile = mobile;
        this.email = email;
    }
}
