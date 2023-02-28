package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.LigneCreanceApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneCreance;
import com.wokite.net.backendstore.services.LigneCreanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class LigneCreanceController implements LigneCreanceApi {

    @Autowired
    private LigneCreanceService ligneCreanceService;

    @Override
    public ResponseEntity<LigneCreance> createLigneCreance(LigneCreance ligneCreance) {
        LigneCreance ligneCreanceResult = ligneCreanceService.saveLigneCreance(ligneCreance);
        return new ResponseEntity<>(ligneCreanceResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LigneCreance> updateLigneCreance(Long lcomId, LigneCreance ligneCreance) {
        ligneCreance.setId(lcomId);
        LigneCreance ligneCreanceResult = ligneCreanceService.saveLigneCreance(ligneCreance);
        return new ResponseEntity<>(ligneCreanceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LigneCreance> getLigneCreanceById(Long id) throws ResourceNotFoundException {
        LigneCreance ligneCreanceResult = ligneCreanceService.findLigneCreanceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LigneCreance " + id + "not found"));
        return new ResponseEntity<>(ligneCreanceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCreance>> getAllLigneCreances() {
        List<LigneCreance> ligneCreanceList = ligneCreanceService.findAllLigneCreances();
        return new ResponseEntity<>(ligneCreanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCreance>> getAllLigneCreanceOrderDesc() {
        List<LigneCreance> ligneCreanceList = ligneCreanceService.findAllLigneCreancesOrderDesc();
        return new ResponseEntity<>(ligneCreanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCreance>> getListLigneCreanceByProduitId(Long prodId) {
        List<LigneCreance> ligneCreanceList = ligneCreanceService
                .findLigneCreanceByProductId(prodId);
        return new ResponseEntity<>(ligneCreanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCreance>> getListLigneCreanceByCreanceId(Long creanceId) {
        List<LigneCreance> ligneCreanceList = ligneCreanceService
                .findLigneCreanceByCreanceId(creanceId);
        return new ResponseEntity<>(ligneCreanceList, HttpStatus.OK);
    }
}
