package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueApprovisionnementApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueApprovisionnement;
import com.wokite.net.backendstore.services.HistoriqueApprovisionnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueApprovisionnementController implements HistoriqueApprovisionnementApi {

    @Autowired
    private HistoriqueApprovisionnementService historiqueApprovisionnementService;

    @Override
    public ResponseEntity<HistoriqueApprovisionnement> createApprovisionnement(HistoriqueApprovisionnement historiqueApprovisionnement) {
        HistoriqueApprovisionnement historiqueApprovisionnementResult = historiqueApprovisionnementService
                .saveHistoriqueApprovisionnement(historiqueApprovisionnement);
        return new ResponseEntity<>(historiqueApprovisionnementResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueApprovisionnement> getHistoriqueApprovisionnementById(Long id) throws ResourceNotFoundException {
        HistoriqueApprovisionnement historiqueApprovisionnementResult = historiqueApprovisionnementService
                .findHistoriqueApprovisionnementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistoriqueApprovisionnement that id is" + id + "not found"));
        return new ResponseEntity<>(historiqueApprovisionnementResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueApprovisionnement>> getAllHistoriqueApprovisionnements() {
        List<HistoriqueApprovisionnement> historiqueApprovisionnementList = historiqueApprovisionnementService
                .findAllHistoriqueApprovisionnements();
        return new ResponseEntity<>(historiqueApprovisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueApprovisionnement>> getAllHistoriqueApprovisionnementsOrderDesc() {
        List<HistoriqueApprovisionnement> historiqueApprovisionnementList = historiqueApprovisionnementService
                .findAllHistoriqueApprovisionnementsOrderDesc();
        return new ResponseEntity<>(historiqueApprovisionnementList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueApprovisionnement() {
        return historiqueApprovisionnementService.countNumberOfHistoriqueApprovisionnements();
    }

    @Override
    public ResponseEntity<?> deleteHistoriqueApprovisionnement(Long id) {
        historiqueApprovisionnementService.deleteHistoriqueApprovisionnement(id);
        return ResponseEntity.ok().build();
    }
}
