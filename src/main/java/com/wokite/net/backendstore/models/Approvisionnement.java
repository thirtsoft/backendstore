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
@Table(name = "approvisionnement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Approvisionnement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    @NotNull
    private Long code;

    @Column(name = "montantAvance", length = 250)
    private Double montantAvance;

    @Column(name = "totalAppro", length = 250)
    private Double totalAppro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    @Column(name = "dateApprovisionnement")
    private Date dateApprovisionnement;

    @Column(name = "status")
    private String status;

    @Column(name = "observation")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "four_id", nullable = false)
    Fournisseur fournisseur;

    @OneToMany(mappedBy = "approvisionnement", fetch = FetchType.LAZY)
    @Valid
    private List<LigneApprovisionnement> ligneApprovisionnements = new ArrayList<>();

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
