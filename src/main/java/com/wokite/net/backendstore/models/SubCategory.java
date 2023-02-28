package com.wokite.net.backendstore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "subcategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
