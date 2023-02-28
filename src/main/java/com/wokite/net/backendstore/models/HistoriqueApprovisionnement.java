package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historiqueApprovisionnement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoriqueApprovisionnement implements Serializable {

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
    @JoinColumn(name = "approId", nullable = false)
    private Approvisionnement approvisionnement;

}
