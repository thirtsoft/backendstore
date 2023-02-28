package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueCreanceApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueCreance;
import com.wokite.net.backendstore.services.HistoriqueCreanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueCreanceController implements HistoriqueCreanceApi {


    @Autowired
    private HistoriqueCreanceService historiqueCreanceService;

    @Override
    public ResponseEntity<HistoriqueCreance> createHistoriqueCreance(HistoriqueCreance historiqueCreance) {
        HistoriqueCreance historiqueCreanceResult = historiqueCreanceService.saveHistoriqueCreance(historiqueCreance);
        return new ResponseEntity<>(historiqueCreanceResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueCreance> getHistoriqueCreanceById(Long id) throws ResourceNotFoundException {
        HistoriqueCreance historiqueCreanceResult = historiqueCreanceService.findHistoriqueCreanceById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historique Commande " + id + "not found"));
        return new ResponseEntity<>(historiqueCreanceResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueCreance>> getAllHistoriqueCreances() {
        List<HistoriqueCreance> historiqueCreanceList = historiqueCreanceService.findAllHistoriqueCreances();
        return new ResponseEntity<>(historiqueCreanceList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueCreance>> getAllHistoriqueCreancesOrderDesc() {
        List<HistoriqueCreance> historiqueCreanceList = historiqueCreanceService.findAllHistoriqueCreancesOrderDesc();
        return new ResponseEntity<>(historiqueCreanceList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueCreances() {
        return historiqueCreanceService.countNumberOfHistoriqueCreances();
    }


    @Override
    public ResponseEntity<?> deleteHistoriqueCreance(Long id) {
        historiqueCreanceService.deleteHistoriqueCreance(id);
        return ResponseEntity.ok()
                .build();
    }

}
