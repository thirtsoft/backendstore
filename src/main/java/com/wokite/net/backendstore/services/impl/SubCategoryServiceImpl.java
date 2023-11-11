package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.SubCategory;
import com.wokite.net.backendstore.repository.SubCategoryRepository;
import com.wokite.net.backendstore.services.SubCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(Long sCatId, SubCategory subCategory) {
        if (!subCategoryRepository.existsById(sCatId)) {
            throw new ResourceNotFoundException("SubCategory Not found");
        }
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(sCatId);

        if (!optionalSubCategory.isPresent()) {
            throw new ResourceNotFoundException("Scategorie Not found");
        }

        SubCategory subCategoryResult = optionalSubCategory.get();
        subCategoryResult.setCode(subCategory.getCode());
        subCategoryResult.setLibelle(subCategory.getLibelle());
        subCategoryResult.setCategory(subCategory.getCategory());

        return subCategoryRepository.save(subCategoryResult);

    }

    @Override
    public Optional<SubCategory> findSubCategoryById(Long sCatId) {
        if (!subCategoryRepository.existsById(sCatId)) {
            throw new ResourceNotFoundException("SubCategory not found");
        }
        return subCategoryRepository.findById(sCatId);
    }

    @Override
    public SubCategory findByLibelle(String libelle) {
        return subCategoryRepository.findByLibelle(libelle);
    }


    @Override
    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<SubCategory> findAllSubCategoriesOrderDesc() {
        return subCategoryRepository.findByOrderByIdDesc();
    }

    @Override
    public List<SubCategory> findSubCategoryByCategoryId(Long catId) {
        return subCategoryRepository.findSubCategoryByCateoryId(catId);
    }

    @Override
    public void deleteSubCategory(Long sCatId) {
        if (!subCategoryRepository.existsById(sCatId)) {
            throw new ResourceNotFoundException("SubCategory not found");
        }
        SubCategory subCategory = subCategoryRepository.findById(sCatId).get();
        subCategory.setActif(false);
        subCategoryRepository.save(subCategory);
    }
}
