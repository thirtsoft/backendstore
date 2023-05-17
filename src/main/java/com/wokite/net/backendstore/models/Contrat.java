package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "contrat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Contrat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", length = 90, unique = true)
    private String reference;

    @Column(name = "nature", length = 155)
    private String nature;

    @Column(name = "montantContrat", length = 150)
    private Double montantContrat;

    @Column(name = "fileContrat")
    private String fileContrat;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "dateDebutContrat", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateDebutContrat;

    @Column(name = "dateFinContrat", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateFinContrat;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

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
