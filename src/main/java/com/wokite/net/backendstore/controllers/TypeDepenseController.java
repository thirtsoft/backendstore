package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.TypeDepenseApi;
import com.wokite.net.backendstore.models.TypeDepense;
import com.wokite.net.backendstore.services.TypeDepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class TypeDepenseController implements TypeDepenseApi {

    @Autowired
    private TypeDepenseService categoryChargeService;

    @Override
    public ResponseEntity<TypeDepense> createCategoryCharge(TypeDepense typeDepense) {
        categoryChargeService.saveCategoryCharge(typeDepense);
        return new ResponseEntity<>(typeDepense, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TypeDepense> updateCategoryCharge(Long catId, TypeDepense typeDepense) {
        typeDepense.setId(catId);
        TypeDepense typeDepenseResult = categoryChargeService.saveCategoryCharge(typeDepense);
        return new ResponseEntity<>(typeDepenseResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeDepense> getCategoryChargeById(Long id) {
        Optional<TypeDepense> optionalCategoryCharge = categoryChargeService.findCategoryChargeById(id);
        if (optionalCategoryCharge.isPresent())
            return new ResponseEntity<>(optionalCategoryCharge.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<TypeDepense>> getAllCategoriesCharges() {
        List<TypeDepense> typeDepenseList = categoryChargeService.findAllCategoryCharges();
        return new ResponseEntity<>(typeDepenseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TypeDepense>> getAllCategoriesChargeOrderDesc() {
        List<TypeDepense> typeDepenseList = categoryChargeService.findAllCategoryChargesOrderDesc();
        return new ResponseEntity<>(typeDepenseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategoryCharge(Long id) {
        categoryChargeService.deleteCategoryCharge(id);
        return ResponseEntity.ok().build();
    }
}
