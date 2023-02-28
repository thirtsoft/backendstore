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
@Table(name = "devis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Devis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numeroDevis", length = 90, unique = true)
    private Long numeroDevis;

    @NotNull
    @Column(name = "totalDevis", length = 150)
    private double totalDevis;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "observation", length = 250)
    private String observation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    @Column(name = "dateDevis")
    private Date dateDevis;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "devis", fetch = FetchType.LAZY)
    @Valid
    private List<LigneDevis> ldevis = new ArrayList<>();



}
