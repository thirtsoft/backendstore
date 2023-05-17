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
@Table(name = "avoir")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Avoir implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", length = 100, unique = true)
    private Long reference;

    @Column(name = "libelle", length = 150)
    private String libelle;

    @Column(name = "soldeAvoir", length = 250)
    private Double soldeAvoir;

    @Column(name = "nbreJours", length = 250)
    private int nbreJours;

    @Column(name = "totalAvoir", length = 250)
    private Double totalAvoir;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    @Column(name = "dateAvoir")
    private Date dateAvoir;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "four_id", nullable = false)
    Fournisseur fournisseur;

    @OneToMany(mappedBy = "avoir", fetch = FetchType.LAZY)
    @Valid
    private List<LigneAvoir> lavoirs = new ArrayList<>();

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
