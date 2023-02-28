package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneApprovisionnementApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueVente;
import com.wokite.net.backendstore.models.LigneApprovisionnement;
import com.wokite.net.backendstore.services.LigneApprovisionnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneApprovisionnementController implements LigneApprovisionnementApi {

    @Autowired private LigneApprovisionnementService ligneApprovisionnementService;

    @Override
    public ResponseEntity<LigneApprovisionnement> createLigneApprovisionnement(LigneApprovisionnement ligneApprovisionnement) {
        LigneApprovisionnement ligneApprovisionnementResult = ligneApprovisionnementService.saveLigneApprovisionnement(ligneApprovisionnement);
        return new ResponseEntity<>(ligneApprovisionnementResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneApprovisionnement> updateLigneApprovisionnement(Long lApproId, LigneApprovisionnement ligneApprovisionnement) {
        ligneApprovisionnement.setId(lApproId);
        LigneApprovisionnement ligneApprovisionnementResult = ligneApprovisionnementService.saveLigneApprovisionnement(ligneApprovisionnement);
        return new ResponseEntity<>(ligneApprovisionnementResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneApprovisionnement> getLigneApprovisionnementById(Long id) throws ResourceNotFoundException {
        LigneApprovisionnement ligneApprovisionnementResult = ligneApprovisionnementService.findLigneApprovisionnementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneApprovisionnement " + id + "not found"));
        return new ResponseEntity<>(ligneApprovisionnementResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneApprovisionnement>> getAllLigneApprovisionnements() {
        List<LigneApprovisionnement> ligneApprovisionnementList = ligneApprovisionnementService.findAllLigneApprovisionnements();
        return new ResponseEntity<>(ligneApprovisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneApprovisionnement>> getAllLigneApprovisionnementOrderDesc() {
        List<LigneApprovisionnement> ligneApprovisionnementList = ligneApprovisionnementService.findAllLigneApprovisionnementsOrderDesc();
        return new ResponseEntity<>(ligneApprovisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneApprovisionnement>> getListLigneApprovisionnementByProduitId(Long prodId) {
        List<LigneApprovisionnement> ligneApprovisionnementList = ligneApprovisionnementService
                .findListLigneApprovisionnementByProductId(prodId);
        return new ResponseEntity<>(ligneApprovisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneApprovisionnement>> getListLigneApprovisionnementByApprovisionnementId(Long approId) {
        List<LigneApprovisionnement> ligneApprovisionnementList = ligneApprovisionnementService
                .findListLigneApprovisionnementByApprovisionnementId(approId);
        return new ResponseEntity<>(ligneApprovisionnementList, HttpStatus.OK);
    }
}
