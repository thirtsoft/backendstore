package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCommande;
import com.wokite.net.backendstore.repository.LigneCommandeRepository;
import com.wokite.net.backendstore.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Override
    public LigneCommande saveLigneCommande(LigneCommande ligneCommande) {
        return ligneCommandeRepository.save(ligneCommande);
    }

    @Override
    public List<LigneCommande> saveListLigneCmd(List<LigneCommande> ligneCommandeList) {
        return ligneCommandeRepository.saveAll(ligneCommandeList);
    }

    @Override
    public LigneCommande updateLigneCommande(Long lCmdId, LigneCommande ligneCommande) {
        if (!ligneCommandeRepository.existsById(lCmdId)) {
            throw new ResourceNotFoundException("Ligne Commande N° " + lCmdId + "not found");
        }
        Optional<LigneCommande> optionalLigneCommande = ligneCommandeRepository.findById(lCmdId);
        if (!optionalLigneCommande.isPresent()) {
            throw new ResourceNotFoundException("Ligne Commande N°" + lCmdId + "not found");
        }

        LigneCommande lcmdClientResult = optionalLigneCommande.get();

        lcmdClientResult.setProduct(ligneCommande.getProduct());
        lcmdClientResult.setCommande(ligneCommande.getCommande());
        lcmdClientResult.setQuantite(ligneCommande.getQuantite());

        return ligneCommandeRepository.save(lcmdClientResult);
    }

    @Override
    public Optional<LigneCommande> findLigneCommandeById(Long lCmdId) {
        if (!ligneCommandeRepository.existsById(lCmdId)) {
            throw new ResourceNotFoundException("Ligne Comande N° " + lCmdId + "not found");
        }
        return ligneCommandeRepository.findById(lCmdId);
    }

    @Override
    public List<LigneCommande> findAllLigneCommande() {
        return ligneCommandeRepository.findAll();
    }

    @Override
    public List<LigneCommande> findAllLigneCommandesOrderDesc() {
        return ligneCommandeRepository.findByOrderByIdDesc();
    }

    @Override
    public List<LigneCommande> findLigneCommandeByProductId(Long prodId) {
        return ligneCommandeRepository.ListLigneCommandeByProductId(prodId);
    }

    @Override
    public List<LigneCommande> findLigneCommandeByCommandeClientId(Long comId) {
        return ligneCommandeRepository.ListLigneCommandeByCommandeClientId(comId);
    }

    @Override
    public List<LigneCommande> findAllLcomByNumero(Long numero) {
        return ligneCommandeRepository.findAllByNumero(numero);
    }

    @Override
    public ResponseEntity<Object> deleteLigneCommande(Long id) {
        if (!ligneCommandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ligne Commande N° " + id + "not found");
        }
        ligneCommandeRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteLcomByNumero(Long numero) {
        if (!ligneCommandeRepository.existsById(numero)) {
            throw new ResourceNotFoundException("Ligne Commande N° " + numero + "not found");
        }
        ligneCommandeRepository.deleteByNumero(numero);
    }
}
