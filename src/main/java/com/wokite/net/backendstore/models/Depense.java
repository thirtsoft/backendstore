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
@Table(name = "depense")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Depense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codeCharge", length = 100, unique = true)
    private String codeCharge;

    @Column(name = "montantCharge", length = 150)
    private Double montantCharge;

    @Column(name = "dateCharge", length = 150)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateCharge;

    @NotNull
    @Column(name = "designation", unique = true)
    private String designation;

    @ManyToOne
    @JoinColumn(name = "type_depense_id")
    private TypeDepense typeDepense;



}
