package com.wokite.net.backendstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantite", length = 60)
    private int quantite;

    private Date dateMiseAJour;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private Product product;

}
