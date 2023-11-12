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
@Table(name = "ligneCommande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCommande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero", length = 100)
    private Long numero;

    @NotNull
    @Column(name = "quantite", length = 60)
    private int quantite;

    @Column(name = "prix", length = 100)
    private Double prix;

    @Column(name = "prixCommande", length = 100)
    private Double prixCommande;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmd_id")
    @JsonIgnoreProperties(value = {"lcomms"})
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @Column(name = "actif")
    private int actif;

    public LigneCommande(Long numero, int quantite, Double prix, Double prixCommande, Product product) {
        this.numero = numero;
        this.quantite = quantite;
        this.prix = prix;
        this.prixCommande = prixCommande;
        this.product = product;
    }

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
