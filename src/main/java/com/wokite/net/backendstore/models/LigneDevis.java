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
@Table(name = "ligneDevis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneDevis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroligneDevis", length = 90, unique = true)
    private Long numeroligneDevis;

    @NotNull
    @Column(name = "quantite", length = 90)
    private int quantite;

    @Column(name = "prix", length = 120)
    private Double prix;

    @Column(name = "prixligneDevis", length = 120)
    private Double prixligneDevis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dev_id")
    @JsonIgnoreProperties(value = {"ldevis"})
    private Devis devis;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

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
