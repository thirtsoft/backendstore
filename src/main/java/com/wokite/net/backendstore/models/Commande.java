package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroCommande", length = 100, unique = true)
    private Long numeroCommande;

    @NotNull
    @Column(name = "totalCommande", length = 150)
    private Double totalCommande;

    @NotNull
    @Column(name = "montantReglement", length = 150)
    private Double montantReglement;

    @NotNull
    @Column(name = "typeReglement", length = 100)
    private String typeReglement;

    @Column(name = "status", length = 60)
    private String status;

    @Column(name = "dateCommande", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateCommande;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    @Valid
    private List<LigneCommande> lcomms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "userId")
   // @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;


    public Commande(Long numeroCommande, Double totalCommande, Double montantReglement, String typeReglement, String status, Date dateCommande) {
        this.numeroCommande = numeroCommande;
        this.totalCommande = totalCommande;
        this.montantReglement = montantReglement;
        this.typeReglement = typeReglement;
        this.status = status;
        this.dateCommande = dateCommande;
    }
}
