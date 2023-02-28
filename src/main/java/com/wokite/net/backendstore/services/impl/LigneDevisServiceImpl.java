package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneDevis;
import com.wokite.net.backendstore.repository.LigneDevisRepository;
import com.wokite.net.backendstore.services.LigneDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneDevisServiceImpl implements LigneDevisService {

    @Autowired
    public LigneDevisRepository ligneDevisRepository;

    @Override
    public LigneDevis saveLigneDevis(LigneDevis ligneDevis) {
        return ligneDevisRepository.save(ligneDevis);
    }

    @Override
    public List<LigneDevis> saveListLigneDevis(List<LigneDevis> ligneDevisList) {
        return ligneDevisRepository.saveAll(ligneDevisList);
    }

    @Override
    public LigneDevis updateLigneDevis(Long ldevId, LigneDevis ligneDevis) {
        if (!ligneDevisRepository.existsById(ldevId)) {
            throw new ResourceNotFoundException("LigneDevis " + ldevId + "not found");
        }
        Optional<LigneDevis> optionalLigneDevis = ligneDevisRepository.findById(ldevId);
        if (!optionalLigneDevis.isPresent()) {
            throw new ResourceNotFoundException("LigneDevis not found");
        }
        LigneDevis ligneDevisResult = optionalLigneDevis.get();
        ligneDevisResult.setNumeroligneDevis(ligneDevis.getNumeroligneDevis());
        ligneDevisResult.setPrix(ligneDevis.getPrix());
        ligneDevisResult.setPrixligneDevis(ligneDevis.getPrixligneDevis());
        ligneDevisResult.setQuantite(ligneDevis.getQuantite());
        ligneDevisResult.setDevis(ligneDevis.getDevis());
        ligneDevisResult.setProduct(ligneDevis.getProduct());

        return ligneDevisRepository.save(ligneDevisResult);
    }

    @Override
    public Optional<LigneDevis> findLigneDevisById(Long ldevId) {
        if (!ligneDevisRepository.existsById(ldevId)) {
            throw new ResourceNotFoundException("LigneDevis " + ldevId + "not found");
        }
        return ligneDevisRepository.findById(ldevId);
    }

    @Override
    public List<LigneDevis> findAllLigneDevis() {
        return ligneDevisRepository.findAll();
    }

    @Override
    public List<LigneDevis> findAllLigneDevissOrderDesc() {
        return ligneDevisRepository.findByOrderByIdDesc();
    }

    @Override
    public List<LigneDevis> findLigneDevisByProductId(Long prodId) {
        return ligneDevisRepository.ListLigneDevisByProductId(prodId);
    }

    @Override
    public List<LigneDevis> findLigneDevisByDevId(Long devId) {
        return ligneDevisRepository.ListLigneDevisByDevisId(devId);
    }

    @Override
    public List<LigneDevis> findAllLigneDevisByNumero(Long numero) {
        return ligneDevisRepository.findAllByNumeroligneDevis(numero);
    }

    @Override
    public void deleteLigneDevis(Long id) {
        if (!ligneDevisRepository.existsById(id)) {
            throw new ResourceNotFoundException("LigneDevis " + id + "not found");
        }
        ligneDevisRepository.deleteById(id);
    }

    @Override
    public void deleteLigneDevisByNumero(Long numero) {
        if (!ligneDevisRepository.existsById(numero)) {
            throw new ResourceNotFoundException("LigneDevis " + numero + "not found");
        }
        ligneDevisRepository.deleteByNumeroligneDevis(numero);
    }
}
