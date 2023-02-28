package com.wokite.net.backendstore.services;

import com.wokite.net.backendstore.models.TypeDepense;

import java.util.List;
import java.util.Optional;

public interface TypeDepenseService {

    TypeDepense saveCategoryCharge(TypeDepense typeDepense);

    TypeDepense updateCategoryCharge(Long catId, TypeDepense typeDepense);

    Optional<TypeDepense> findCategoryChargeById(Long catId);

    List<TypeDepense> findAllCategoryCharges();

    List<TypeDepense> findAllCategoryChargesOrderDesc();

    void deleteCategoryCharge(Long catId);

}
