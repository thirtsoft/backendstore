package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneCommandeApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCommande;
import com.wokite.net.backendstore.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneCommandeController implements LigneCommandeApi {

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @Override
    public ResponseEntity<LigneCommande> createLigneCommande(LigneCommande ligneCommande) {
        LigneCommande ligneCommandeResult = ligneCommandeService.saveLigneCommande(ligneCommande);
        return new ResponseEntity<>(ligneCommandeResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneCommande> updateLigneCommande(Long lcomId, LigneCommande ligneCommande) {
        ligneCommande.setId(lcomId);
        LigneCommande ligneCommandeResult = ligneCommandeService.saveLigneCommande(ligneCommande);
        return new ResponseEntity<>(ligneCommandeResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneCommande> getLigneCommandeById(Long id) throws ResourceNotFoundException {
        LigneCommande ligneCommandeResult = ligneCommandeService.findLigneCommandeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCommande " + id + "not found"));
        return new ResponseEntity<>(ligneCommandeResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommande>> getAllLigneCommandes() {
        List<LigneCommande> ligneCommandeList = ligneCommandeService.findAllLigneCommande();
        return new ResponseEntity<>(ligneCommandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommande>> getAllLigneCommandeOrderDesc() {
        List<LigneCommande> ligneCommandeList = ligneCommandeService.findAllLigneCommandesOrderDesc();
        return new ResponseEntity<>(ligneCommandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommande>> getListLigneCommandeByProduitId(Long prodId) {
        List<LigneCommande> ligneCommandeList = ligneCommandeService
                .findLigneCommandeByProductId(prodId);
        return new ResponseEntity<>(ligneCommandeList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommande>> getListLigneCommandeByCommandeId(Long comId) {
        List<LigneCommande> ligneCommandeList = ligneCommandeService
                .findLigneCommandeByCommandeClientId(comId);
        return new ResponseEntity<>(ligneCommandeList, HttpStatus.OK);
    }
}
