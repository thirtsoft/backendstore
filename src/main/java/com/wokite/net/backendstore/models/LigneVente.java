package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ligneVente")
public class LigneVente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
   // @Column(name = "numeroVente", length = 100, unique = true)
    @Column(name = "numeroVente", length = 100)
    private Long numeroVente;

    @NotNull
    @Column(name = "code", length = 100, unique = true)
    private String code;

    @NotNull
    @Column(name = "quantite", length = 60)
    private int quantite;

    @Column(name = "prixVente", length = 200)
    private Double prixVente;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    @JsonIgnoreProperties(value = {"ligneVentes"})
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
