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
@Table(name = "ligneAvoir")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneAvoir implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroAvoir", length = 100, unique = true)
    private Long numeroAvoir;

    @NotNull
    @Column(name = "quantite", length = 60)
    private int quantite;

    @Column(name = "prixLigneAvoir", length = 200)
    private Double prixLigneAvoir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avoir_id")
    @JsonIgnoreProperties(value = {"lavoirs"})
    private Avoir avoir;

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
