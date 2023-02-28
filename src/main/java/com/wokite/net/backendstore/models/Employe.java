package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "employe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cni", length = 100, unique = true)
    private String cni;

    @Column(name = "prenom", length = 150)
    private String prenom;

    @Column(name = "nom", length = 90)
    private String nom;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "telephone", length = 50, unique = true)
    private String telephone;

    @Column(name = "telephone2", length = 50, unique = true)
    private String telephone2;

    @Email
    @Column(name = "email", length = 90, unique = true)
    private String email;

}
