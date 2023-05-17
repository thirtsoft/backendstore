package com.wokite.net.backendstore.models;

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
@Table(name = "creance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "reference"
                ),
                @UniqueConstraint(
                        columnNames = "codeCreance"
                )
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Creance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", length = 90, unique = true)
    private Long reference;

    @NotNull
    @Column(name = "codeCreance", length = 90, unique = true)
    private String codeCreance;

    @Column(name = "libelle", length = 150)
    private String libelle;

    @Column(name = "soldeCreance", length = 155)
    private Double soldeCreance;

    @Column(name = "avanceCreance", length = 155)
    private Double avanceCreance;

    @Column(name = "totalCreance", length = 155)
    private Double totalCreance;

    @Column(name = "nbreJours", length = 35)
    private int nbreJours;

    @Column(name = "status", length = 90)
    private String status;

    /*   @Column(name = "dateCreance")
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")*/
    private Date dateCreance;

    @OneToMany(mappedBy = "creance", fetch = FetchType.LAZY)
    @Valid
    private List<LigneCreance> lcreances = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

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



}
