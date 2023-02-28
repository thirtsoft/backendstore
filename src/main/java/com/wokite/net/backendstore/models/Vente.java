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
@Table(name = "vente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroVente", length = 100, unique = true)
    private Long numeroVente;

    @NotNull
    @Column(name = "totalVente", length = 150)
    private Double totalVente;

    @NotNull
    @Column(name = "montantReglement", length = 150)
    private Double montantReglement;

    @NotNull
    @Column(name = "typeReglement", length = 100)
    private String typeReglement;

    @Column(name = "status", length = 60)
    private String status;

    @Column(name = "dateVente", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateVente;

    @OneToMany(mappedBy = "vente", fetch = FetchType.LAZY)
    @Valid
    private List<LigneVente> ligneVentes = new ArrayList<>();

    @ManyToOne
  //  @JoinColumn(name = "userId", nullable = false)
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;


}
