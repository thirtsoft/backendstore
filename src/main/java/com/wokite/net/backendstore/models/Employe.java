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
@Table(name = "employe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cni", length = 100, unique = true)
    private String cni;

    @NotNull
    @Column(name = "prenom", length = 150)
    private String prenom;

    @NotNull
    @Column(name = "nom", length = 90)
    private String nom;

    @Column(name = "address", length = 150)
    private String address;

    @NotNull
    @Column(name = "telephone", length = 50, unique = true)
    private String telephone;

    @Column(name = "telephone2", length = 50, unique = true)
    private String telephone2;

    @Email
    @Column(name = "email", length = 90, unique = true)
    private String email;

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

}
