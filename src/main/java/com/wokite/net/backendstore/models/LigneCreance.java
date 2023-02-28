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
@Table(name = "ligneCreance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCreance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroLigneCreance", length = 100)
    private Long numeroLigneCreance;

    @NotNull
    @Column(name = "quantite", length = 60)
    private int quantite;

    @Column(name = "prix", length = 200)
    private Double prix;

    @Column(name = "prixLigneCreance", length = 200)
    private Double prixLigneCreance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creance_id")
    @JsonIgnoreProperties(value = {"lcreances"})
    private Creance creance;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

}
