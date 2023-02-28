package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {

    List<LigneVente> findAllByNumeroVente(Long numeroVente);

    Optional<LigneVente> findByCode(String code);

    List<LigneVente> findAllByCode(String code);

    @Modifying
    @Query("delete from LigneVente where numeroVente = :numero")
    void deleteByNumeroVente(@Param("numero") Long numero);

    @Query("select p from LigneVente p where p.product.id =:prod")
    List<LigneVente> ListLigneVenteByProductId(@Param("prod") Long prodId);

    @Query("select p from LigneVente p where p.vente.id =:num")
    List<LigneVente> ListLigneVenteByVenteId(@Param("num") Long venteId);

    List<LigneVente> findByOrderByIdDesc();

    List<LigneVente> findTop200ByOrderByQuantiteDesc();
}
