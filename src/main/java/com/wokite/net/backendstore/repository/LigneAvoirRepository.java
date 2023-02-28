package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.LigneAvoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneAvoirRepository extends JpaRepository<LigneAvoir, Long> {

    List<LigneAvoir> findAllByNumeroAvoir(Long numeroAvoir);

    @Modifying
    @Query("delete from LigneAvoir where numeroAvoir = :numero")
    void deleteByNumeroAvoir(@Param("numero") Long numeroAvoir);

    @Query("select p from LigneAvoir p where p.product.id =:prod")
    List<LigneAvoir> ListLigneAvoirByProductId(@Param("prod") Long prodId);

    @Query("select p from LigneAvoir p where p.avoir.id =:num")
    List<LigneAvoir> ListLigneAvoirByAvoirId(@Param("num") Long avoirId);

    List<LigneAvoir> findByOrderByIdDesc();
}
