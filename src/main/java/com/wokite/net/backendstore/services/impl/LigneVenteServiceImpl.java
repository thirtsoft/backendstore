package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneVente;
import com.wokite.net.backendstore.repository.LigneVenteRepository;
import com.wokite.net.backendstore.services.LigneVenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneVenteServiceImpl implements LigneVenteService {


    private final LigneVenteRepository ligneVenteRepository;

    public LigneVenteServiceImpl(LigneVenteRepository ligneVenteRepository) {
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public LigneVente saveLigneVente(LigneVente ligneVente) {
        return ligneVenteRepository.save(ligneVente);
    }

    @Override
    public LigneVente updateLigneVente(Long lventeId, LigneVente ligneVente) {
        if (!ligneVenteRepository.existsById(lventeId)) {
            throw new ResourceNotFoundException("LigneVente " + lventeId + "not found");
        }
        Optional<LigneVente> optionalLigneVente = ligneVenteRepository.findById(lventeId);
        if (!optionalLigneVente.isPresent()) {
            throw new ResourceNotFoundException("LigneDevis not found");
        }
        LigneVente ligneVenteResult = optionalLigneVente.get();
        ligneVenteResult.setNumeroVente(ligneVente.getNumeroVente());
        ligneVenteResult.setCode(ligneVente.getCode());
        ligneVenteResult.setPrixVente(ligneVente.getPrixVente());
        ligneVenteResult.setQuantite(ligneVente.getQuantite());
        ligneVenteResult.setVente(ligneVente.getVente());
        ligneVenteResult.setProduct(ligneVente.getProduct());

        return ligneVenteRepository.save(ligneVenteResult);
    }

    @Override
    public Optional<LigneVente> findLigneVenteById(Long lventeId) {
        if (!ligneVenteRepository.existsById(lventeId)) {
            throw new ResourceNotFoundException("LigneVente " + lventeId + "not found");
        }
        return ligneVenteRepository.findById(lventeId);
    }

    @Override
    public List<LigneVente> findAllLigneVentes() {
        return ligneVenteRepository.findAll();
    }

    @Override
    public List<LigneVente> findAllLigneVentesOrderDesc() {
        return ligneVenteRepository.findByOrderByIdDesc();
    }

    @Override
    public List<LigneVente> findLigneVenteByProduitId(Long prodId) {
        return ligneVenteRepository.ListLigneVenteByProductId(prodId);
    }

    @Override
    public List<LigneVente> findLigneVenteByVenteId(Long venteId) {
        return ligneVenteRepository.ListLigneVenteByVenteId(venteId);
    }

    @Override
    public ResponseEntity<Object> deleteLigneVente(Long ligneVenteId) {
        return null;
    }

    @Override
    public List<LigneVente> findTop200ByOrderByIdDesc() {
        return ligneVenteRepository.findTop200ByOrderByQuantiteDesc();
    }

    @Override
    public void deleteLventeByNumero(Long numero) {
        if (!ligneVenteRepository.existsById(numero)) {
            throw new ResourceNotFoundException("LigneVente " + numero + "not found");
        }
        ligneVenteRepository.deleteByNumeroVente(numero);
    }
}
