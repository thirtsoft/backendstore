package com.wokite.net.backendstore.repository;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    SubCategory findByLibelle(String libelle);

    @Query("select p from SubCategory p where p.category.id =:cat")
    List<SubCategory> findSubCategoryByCateoryId(@Param("cat") Long catId);

    List<SubCategory> findByOrderByIdDesc();

}
