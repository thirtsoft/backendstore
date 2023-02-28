package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    List<LigneCommande> findAllByNumero(Long numero);

    @Modifying
    @Query("delete from LigneCommande where numero = :numero")
    void deleteByNumero(@Param("numero") Long numero);

    @Query("select p from LigneCommande p where p.product.id =:prod")
    List<LigneCommande> ListLigneCommandeByProductId(@Param("prod") Long prodId);

    @Query("select p from LigneCommande p where p.commande.id =:num")
    List<LigneCommande> ListLigneCommandeByCommandeClientId(@Param("num") Long comId);

    List<LigneCommande> findByOrderByIdDesc();
}
