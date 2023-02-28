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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
