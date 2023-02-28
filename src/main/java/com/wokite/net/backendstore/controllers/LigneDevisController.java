package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneDevisApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneDevis;
import com.wokite.net.backendstore.services.LigneDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneDevisController implements LigneDevisApi {

    @Autowired
    private LigneDevisService ligneDevisService;

    @Override
    public ResponseEntity<LigneDevis> createLigneDevis(LigneDevis ligneDevis) {
        LigneDevis ligneDevisResult = ligneDevisService.saveLigneDevis(ligneDevis);
        return new ResponseEntity<>(ligneDevisResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneDevis> updateLigneDevis(Long ldevisId, LigneDevis ligneDevis) {
        ligneDevis.setId(ldevisId);
        LigneDevis ligneDevisResult = ligneDevisService.saveLigneDevis(ligneDevis);
        return new ResponseEntity<>(ligneDevisResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneDevis> getLigneDevisById(Long id) throws ResourceNotFoundException {
        LigneDevis ligneDevisResult = ligneDevisService.findLigneDevisById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneDevis " + id + "not found"));
        return new ResponseEntity<>(ligneDevisResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneDevis>> getAllLigneDeviss() {
        List<LigneDevis> ligneDevisList = ligneDevisService.findAllLigneDevis();
        return new ResponseEntity<>(ligneDevisList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneDevis>> getAllLigneDevisOrderDesc() {
        List<LigneDevis> ligneDevisList = ligneDevisService.findAllLigneDevissOrderDesc();
        return new ResponseEntity<>(ligneDevisList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneDevis>> getListLigneDevisByProduitId(Long prodId) {
        List<LigneDevis> ligneDevisList = ligneDevisService
                .findLigneDevisByProductId(prodId);
        return new ResponseEntity<>(ligneDevisList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneDevis>> getListLigneDevisByDevisId(Long devisId) {
        List<LigneDevis> ligneDevisList = ligneDevisService
                .findLigneDevisByDevId(devisId);
        return new ResponseEntity<>(ligneDevisList, HttpStatus.OK);
    }
}
