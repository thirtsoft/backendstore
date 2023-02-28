package com.wokite.net.backendstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "typedepense")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeDepense implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codeCategoryCharge", length = 80, unique = true)
    private String codeCategoryCharge;

    @NotNull
    @Column(name = "designation", unique = true)
    private String designation;

}
