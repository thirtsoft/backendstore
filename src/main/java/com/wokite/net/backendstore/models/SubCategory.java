package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "subcategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "code", length = 100, unique = true)
    private String code;

    @Column(name = "libelle", length = 150)
    @NotNull
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

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
