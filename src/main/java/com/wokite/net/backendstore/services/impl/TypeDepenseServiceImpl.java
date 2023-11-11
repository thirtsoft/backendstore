package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.TypeDepense;
import com.wokite.net.backendstore.repository.TypeDepenseRepository;
import com.wokite.net.backendstore.services.TypeDepenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeDepenseServiceImpl implements TypeDepenseService {

    private final TypeDepenseRepository typeDepenseRepository;

    public TypeDepenseServiceImpl(TypeDepenseRepository typeDepenseRepository) {
        this.typeDepenseRepository = typeDepenseRepository;
    }

    @Override
    public TypeDepense saveCategoryCharge(TypeDepense typeDepense) {
        return typeDepenseRepository.save(typeDepense);
    }

    @Override
    public TypeDepense updateCategoryCharge(Long catId, TypeDepense typeDepense) {
        if (!typeDepenseRepository.existsById(catId)) {
            throw new ResourceNotFoundException("This CategoryCharge is not found");
        }
        Optional<TypeDepense> optionalCategoryCharge = typeDepenseRepository.findById(catId);
        if (!optionalCategoryCharge.isPresent()) {
            throw new ResourceNotFoundException("This CategoryCharge is not found");
        }
        TypeDepense typeDepenseResult = optionalCategoryCharge.get();
        typeDepenseResult.setDesignation(typeDepense.getDesignation());
        return typeDepenseRepository.save(typeDepenseResult);
    }

    @Override
    public Optional<TypeDepense> findCategoryChargeById(Long catId) {
        if (!typeDepenseRepository.existsById(catId)) {
            throw new ResourceNotFoundException("This CategoryCharge is not found");
        }
        return typeDepenseRepository.findById(catId);
    }


    @Override
    public List<TypeDepense> findAllCategoryCharges() {
        return typeDepenseRepository.findAll();
    }

    @Override
    public List<TypeDepense> findAllCategoryChargesOrderDesc() {
        return typeDepenseRepository.findByOrderByIdDesc();
    }

    @Override
    public void deleteCategoryCharge(Long catId) {
        if (!typeDepenseRepository.existsById(catId)) {
            throw new ResourceNotFoundException("This CategoryCharge is not found");
        }
        TypeDepense typeDepense = typeDepenseRepository.findById(catId).get();
        typeDepense.setActif(false);
        typeDepenseRepository.save(typeDepense);
    }
}
