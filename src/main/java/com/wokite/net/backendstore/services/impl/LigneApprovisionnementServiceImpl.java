package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneApprovisionnement;
import com.wokite.net.backendstore.repository.LigneApprovisionnementRepository;
import com.wokite.net.backendstore.services.LigneApprovisionnementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneApprovisionnementServiceImpl implements LigneApprovisionnementService {

    private final LigneApprovisionnementRepository ligneApprovisionnementRepository;

    public LigneApprovisionnementServiceImpl(LigneApprovisionnementRepository ligneApprovisionnementRepository) {
        this.ligneApprovisionnementRepository = ligneApprovisionnementRepository;
    }

    @Override
    public LigneApprovisionnement saveLigneApprovisionnement(LigneApprovisionnement ligneApprovisionnement) {
        return ligneApprovisionnementRepository.save(ligneApprovisionnement);
    }

    @Override
    public LigneApprovisionnement updateLigneApprovisionnement(Long lApproId, LigneApprovisionnement ligneApprovisionnement) {
        if (!ligneApprovisionnementRepository.existsById(lApproId)) {
            throw new ResourceNotFoundException("Ligne Approvisionnement N° " + lApproId + "not found");
        }
        Optional<LigneApprovisionnement> lAppro = ligneApprovisionnementRepository.findById(lApproId);
        if (!lAppro.isPresent()) {
            throw new ResourceNotFoundException("Ligne Approvisionnement N° " + lApproId + "not found");
        }
        LigneApprovisionnement lApproResultat = lAppro.get();

        lApproResultat.setNumeroligneApprovisionnement(ligneApprovisionnement.getNumeroligneApprovisionnement());
        lApproResultat.setPrix(ligneApprovisionnement.getPrix());
        lApproResultat.setQuantite(ligneApprovisionnement.getQuantite());
        lApproResultat.setApprovisionnement(ligneApprovisionnement.getApprovisionnement());
        lApproResultat.setProduct(ligneApprovisionnement.getProduct());

        return ligneApprovisionnementRepository.save(lApproResultat);
    }

    @Override
    public Optional<LigneApprovisionnement> findLigneApprovisionnementById(Long lApproId) {
        if (!ligneApprovisionnementRepository.existsById(lApproId)) {
            throw new ResourceNotFoundException("Ligne Approvisionement Not found");
        }
        return ligneApprovisionnementRepository.findById(lApproId);
    }

    @Override
    public List<LigneApprovisionnement> findAllLigneApprovisionnements() {
        return ligneApprovisionnementRepository.findAll();
    }

    @Override
    public List<LigneApprovisionnement> findAllLigneApprovisionnementsOrderDesc() {
        return ligneApprovisionnementRepository.findByOrderByIdDesc();
    }

    @Override
    public List<LigneApprovisionnement> findAllLApproByNumero(Long numero) {
        return ligneApprovisionnementRepository.findAllByNumeroligneApprovisionnement(numero);
    }

    @Override
    public List<LigneApprovisionnement> findListLigneApprovisionnementByProductId(Long prodId) {
        return ligneApprovisionnementRepository.findListLigneApprovisionnementByProductId(prodId);
    }

    @Override
    public List<LigneApprovisionnement> findListLigneApprovisionnementByApprovisionnementId(Long approId) {
        return ligneApprovisionnementRepository.findListLigneApprovisionnementByApprovisionnementId(approId);
    }

    @Override
    public void deleteLApproByNumero(Long numero) {
        if (!ligneApprovisionnementRepository.existsById(numero)) {
            throw new ResourceNotFoundException("Ligne Approvisionement Not found");
        }
        ligneApprovisionnementRepository.deleteByNumeroligneApprovisionnement(numero);
    }
}
