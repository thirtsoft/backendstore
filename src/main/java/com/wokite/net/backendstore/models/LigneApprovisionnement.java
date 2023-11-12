package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ligne_approvisionnement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneApprovisionnement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroligneApprovisionnement", length = 100)
    private Long numeroligneApprovisionnement;

    @NotNull
    @Column(name = "quantite", length = 50)
    private int quantite;

    @Column(name = "prix", length = 50)
    private Double prix;

    @Column(name = "prixLigneAppro", length = 50)
    private Double prixLigneAppro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Appro_id")
    @JsonIgnoreProperties(value = {"ligneApprovisionnements"})
    private Approvisionnement approvisionnement;

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
