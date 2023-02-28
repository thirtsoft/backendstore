package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneAvoir;
import com.wokite.net.backendstore.repository.LigneAvoirRepository;
import com.wokite.net.backendstore.services.LigneAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneAvoirServiceImpl implements LigneAvoirService {

    @Autowired
    private LigneAvoirRepository ligneAvoirRepository;

    @Override
    public LigneAvoir saveLigneAvoir(LigneAvoir ligneAvoir) {
        return ligneAvoirRepository.save(ligneAvoir);
    }

    @Override
    public LigneAvoir updateLigneAvoir(Long lAvoirId, LigneAvoir ligneAvoir) {
        if (!ligneAvoirRepository.existsById(lAvoirId)) {
            throw new ResourceNotFoundException("This LigneAvoir is not found");
        }
        Optional<LigneAvoir> optionalLigneAvoir = ligneAvoirRepository.findById(lAvoirId);
        if (!optionalLigneAvoir.isPresent()) {
            throw new ResourceNotFoundException("This LigneAvoir is not found");
        }
        LigneAvoir ligneAvoirResult = optionalLigneAvoir.get();

        ligneAvoirResult.setNumeroAvoir(ligneAvoir.getNumeroAvoir());
        ligneAvoirResult.setAvoir(ligneAvoir.getAvoir());
        ligneAvoirResult.setProduct(ligneAvoir.getProduct());
        ligneAvoirResult.setQuantite(ligneAvoir.getQuantite());

        return ligneAvoirRepository.save(ligneAvoirResult);
    }

    @Override
    public Optional<LigneAvoir> findLigneAvoirById(Long lAvoirId) {
        if (!ligneAvoirRepository.existsById(lAvoirId)) {
            throw new ResourceNotFoundException("This LigneAvoir is not found");
        }
        return ligneAvoirRepository.findById(lAvoirId);
    }

    @Override
    public List<LigneAvoir> findAllLigneAvoirs() {
        return ligneAvoirRepository.findAll();
    }

    @Override
    public List<LigneAvoir> findAllLigneAvoirsOrderDesc() {
        return ligneAvoirRepository.findByOrderByIdDesc();
    }

    @Override
    public List<LigneAvoir> findAllLApproByNumero(Long numero) {
        return ligneAvoirRepository.findAllByNumeroAvoir(numero);
    }

    @Override
    public List<LigneAvoir> findListLigneAvoirByProductId(Long prodId) {
        return ligneAvoirRepository.ListLigneAvoirByProductId(prodId);
    }

    @Override
    public List<LigneAvoir> findListLigneAvoirByAvoirId(Long approId) {
        return ligneAvoirRepository.ListLigneAvoirByAvoirId(approId);
    }

    @Override
    public void deleteLAVoirByNumero(Long numero) {
        if (!ligneAvoirRepository.existsById(numero)) {
            throw new ResourceNotFoundException("This LigneAvoir is not found");
        }
        ligneAvoirRepository.deleteByNumeroAvoir(numero);
    }
}
