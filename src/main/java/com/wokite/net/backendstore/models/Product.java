package com.wokite.net.backendstore.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barcode", unique = true)
//    @NotNull
    private String barCode;

    @Column(name = "reference", unique = true)
    @NotNull
    private String reference;

    @NotNull
    @Column(name = "designation")
    private String designation;

    @NotNull
    @Column(name = "prixAchat")
    private Double prixAchat;

    @NotNull
    @Column(name = "prixVente")
    private Double prixVente;

    @NotNull
    @Column(name = "prixDetail")
    private Double prixDetail;

    @NotNull
    @Column(name = "qtestock")
    private int qtestock;

    @NotNull
    @Column(name = "stockInitial")
    private int stockInitial;

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

    @ManyToOne
    @JoinColumn(name = "subcat_id", nullable = false)
    private SubCategory subCategory;

    public Product(Long id, String barCode, String designation, Double prixAchat, Double prixVente, int qtestock, SubCategory subCategory) {
        this.id = id;
        this.barCode = barCode;
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.qtestock = qtestock;
        this.subCategory = subCategory;
    }


}
