package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Versement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersementRepository extends JpaRepository<Versement, Long> {

    @Query("select v from Versement v where v.employe.id =:emp")
    List<Versement> findVersementByEmployeId(@Param("emp") Long empId);

    List<Versement> findByOrderByIdDesc();

}
