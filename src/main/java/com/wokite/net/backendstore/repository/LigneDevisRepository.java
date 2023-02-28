package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.LigneDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneDevisRepository extends JpaRepository<LigneDevis, Long> {

    List<LigneDevis> findAllByNumeroligneDevis(Long numeroligneDevis);

    @Modifying
    @Query("delete from LigneDevis where numeroligneDevis = :numero")
    void deleteByNumeroligneDevis(@Param("numero") Long numeroligneDevis);

    @Query("select p from LigneDevis p where p.product.id =:prod")
    List<LigneDevis> ListLigneDevisByProductId(@Param("prod") Long prodId);

    @Query("select p from LigneDevis p where p.devis.id =:num")
    List<LigneDevis> ListLigneDevisByDevisId(@Param("num") Long devId);

    List<LigneDevis> findByOrderByIdDesc();
}
