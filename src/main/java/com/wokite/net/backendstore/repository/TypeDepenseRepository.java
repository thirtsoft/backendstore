package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.TypeDepense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDepenseRepository extends JpaRepository<TypeDepense, Long> {

    @Query("select c from TypeDepense c where c.designation like :des")
    TypeDepense findByDesignation(@Param("des") String designation);

    @Query("select c from TypeDepense c where c.designation like :des")
    List<TypeDepense> ListCategoryByDesignation(@Param("des") String designation);

    List<TypeDepense> findByOrderByIdDesc();

}
