package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vente")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroVente", length = 100, unique = true)
    private Long numeroVente;

    @NotNull
    @Column(name = "totalVente", length = 150)
    private Double totalVente;

    @NotNull
    @Column(name = "montantReglement", length = 150)
    private Double montantReglement;

    @NotNull
    @Column(name = "typeReglement", length = 100)
    private String typeReglement;

    @Column(name = "status", length = 60)
    private String status;

    @Column(name = "dateVente", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateVente;

    @OneToMany(mappedBy = "vente", fetch = FetchType.LAZY)
    @Valid
    private List<LigneVente> ligneVentes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;

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

    public Long getNumeroVente() {
        return numeroVente;
    }

    public void setNumeroVente(Long numeroVente) {
        this.numeroVente = numeroVente;
    }

    public Double getTotalVente() {
        return totalVente;
    }

    public void setTotalVente(Double totalVente) {
        this.totalVente = totalVente;
    }

    public Double getMontantReglement() {
        return montantReglement;
    }

    public void setMontantReglement(Double montantReglement) {
        this.montantReglement = montantReglement;
    }

    public String getTypeReglement() {
        return typeReglement;
    }

    public void setTypeReglement(String typeReglement) {
        this.typeReglement = typeReglement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public List<LigneVente> getLigneVentes() {
        return ligneVentes;
    }

    public void setLigneVentes(List<LigneVente> ligneVentes) {
        this.ligneVentes = ligneVentes;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
