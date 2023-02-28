package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    Fournisseur findByCode(String code);

    @Query("select count(f) from Fournisseur f ")
    Integer countNumberOfFournisseurs();

    List<Fournisseur> findByOrderByIdDesc();

}
