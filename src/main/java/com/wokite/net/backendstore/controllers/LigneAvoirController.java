package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneAvoirApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneAvoir;
import com.wokite.net.backendstore.services.LigneAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneAvoirController implements LigneAvoirApi {

    @Autowired
    private LigneAvoirService ligneAvoirService;

    @Override
    public ResponseEntity<LigneAvoir> createLigneAvoir(LigneAvoir ligneAvoir) {
        LigneAvoir ligneAvoirResult = ligneAvoirService.saveLigneAvoir(ligneAvoir);
        return new ResponseEntity<>(ligneAvoirResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneAvoir> updateLigneAvoir(Long lAvoirId, LigneAvoir ligneAvoir) {
        ligneAvoir.setId(lAvoirId);
        LigneAvoir ligneAvoirResult = ligneAvoirService.saveLigneAvoir(ligneAvoir);
        return new ResponseEntity<>(ligneAvoirResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneAvoir> getLigneAvoirById(Long id) throws ResourceNotFoundException {
        LigneAvoir ligneAvoirResult = ligneAvoirService.findLigneAvoirById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneAvoir " + id + "not found"));
        return new ResponseEntity<>(ligneAvoirResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneAvoir>> getAllLigneAvoirs() {
        List<LigneAvoir> ligneAvoirList = ligneAvoirService.findAllLigneAvoirs();
        return new ResponseEntity<>(ligneAvoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneAvoir>> getAllLigneAvoirOrderDesc() {
        List<LigneAvoir> ligneAvoirList = ligneAvoirService.findAllLigneAvoirsOrderDesc();
        return new ResponseEntity<>(ligneAvoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneAvoir>> getListLigneAvoirByProduitId(Long prodId) {
        List<LigneAvoir> ligneAvoirList = ligneAvoirService
                .findListLigneAvoirByProductId(prodId);
        return new ResponseEntity<>(ligneAvoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneAvoir>> getListLigneAvoirByAvoirId(Long avoirId) {
        List<LigneAvoir> ligneAvoirList = ligneAvoirService
                .findListLigneAvoirByAvoirId(avoirId);
        return new ResponseEntity<>(ligneAvoirList, HttpStatus.OK);
    }
}
