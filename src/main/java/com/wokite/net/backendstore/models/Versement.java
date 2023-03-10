package com.wokite.net.backendstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "versement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Versement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numVersement", length = 100, unique = true)
    private String numVersement;

    @NotNull
    @Column(name = "numeroRecu", length = 100, unique = true)
    private String numeroRecu;

    @NotNull
    @Column(name = "montantVersement", length = 200)
    private Double montantVersement;

    @Column(name = "motif", length = 200)
    private String motif;

    @Column(name = "fileVersement", length = 200)
    private String fileVersement;

    @Column(name = "dateVersement", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateVersement;

    @ManyToOne
    @JoinColumn(name = "empId", nullable = false)
    private Employe employe;


}
