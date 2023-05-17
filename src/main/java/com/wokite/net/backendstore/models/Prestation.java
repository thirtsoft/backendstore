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
@Table(name = "prestation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Prestation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "designation", length = 150)
    private String designation;

    @NotNull
    @Column(name = "montant", length = 150)
    private Double montant;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    @Column(name = "datePrestation", length = 150)
    private Date datePrestation;

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
