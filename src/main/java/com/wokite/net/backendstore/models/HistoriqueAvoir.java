package com.wokite.net.backendstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historiqueAvoir")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HistoriqueAvoir implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "action", length = 100)
    private String action;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "createdDate", length = 90)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "avoirId", nullable = false)
    private Avoir avoir;

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
