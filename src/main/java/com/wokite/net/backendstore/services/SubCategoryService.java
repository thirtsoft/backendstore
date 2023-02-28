package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.Category;
import com.wokite.net.backendstore.models.SubCategory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface SubCategoryService {

    SubCategory saveSubCategory(SubCategory subCategory);

    SubCategory updateSubCategory(Long sCatId, SubCategory subCategory);

    Optional<SubCategory> findSubCategoryById(Long sCatId);

    SubCategory findByLibelle(String libelle);

    List<SubCategory> findAllSubCategories();

    List<SubCategory> findAllSubCategoriesOrderDesc();

    List<SubCategory> findSubCategoryByCategoryId(Long catId);

    void deleteSubCategory(Long sCatId);


}
