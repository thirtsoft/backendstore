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
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codeClient", length = 90, unique = true)
    private String codeClient;

    @NotNull
    @Column(name = "raisonSocial", length = 155)
    private String raisonSocial;

    @NotNull
    @Column(name = "address", length = 210)
    private String address;

    @Column(name = "telephone", length = 30, unique = true)
    private String telephone;

    @NotNull
    @Column(name = "mobile", length = 25, unique = true)
    private String mobile;

    @Email
    @Column(name = "email", length = 150, unique = true)
    private String email;

    private String subject;

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

    public Client(Long id, String raisonSocial, String mobile, String email) {
        this.id = id;
        this.raisonSocial = raisonSocial;
        this.mobile = mobile;
        this.email = email;
    }
}
