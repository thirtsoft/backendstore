package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueDepenseApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueDepense;
import com.wokite.net.backendstore.services.HistoriqueDepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueDepenseController implements HistoriqueDepenseApi {

    @Autowired
    private HistoriqueDepenseService historiqueDepenseService;

    @Override
    public ResponseEntity<HistoriqueDepense> createHistoriqueCharge(HistoriqueDepense historiqueDepense) {
        HistoriqueDepense historiqueDepenseResult = historiqueDepenseService.saveHistoriqueCharge(historiqueDepense);
        return new ResponseEntity<>(historiqueDepenseResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueDepense> getHistoriqueChargeById(Long id) throws ResourceNotFoundException {
        HistoriqueDepense historiqueDepenseResult = historiqueDepenseService.findHistoriqueChargeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistoriqueCharge that id is" + id + "not found"));
        return new ResponseEntity<>(historiqueDepenseResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueDepense>> getAllHistoriqueCharges() {
        List<HistoriqueDepense> historiqueDepenseList = historiqueDepenseService.findAllHistoriqueCharges();
        return new ResponseEntity<>(historiqueDepenseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueDepense>> getAllHistoriqueChargesOrderDesc() {
        List<HistoriqueDepense> historiqueDepenseList = historiqueDepenseService.findAllHistoriqueChargesOrderDesc();
        return new ResponseEntity<>(historiqueDepenseList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumbersOfHistoriqueCharges() {
        return historiqueDepenseService.countNumberOfHistoriqueCharges();
    }

    @Override
    public ResponseEntity<?> deleteHistoriqueCharge(Long id) {
        historiqueDepenseService.deleteHistoriqueCharge(id);
        return ResponseEntity.ok()
                .build();
    }
}
