package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.PrestationApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Prestation;
import com.wokite.net.backendstore.services.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class PrestationController implements PrestationApi {

    @Autowired
    private PrestationService prestationService;

    @Override
    public ResponseEntity<Prestation> createPrestation(Prestation prestation) {
        prestation.setDatePrestation(new Date());
        Prestation prestationResult = prestationService.savePrestation(prestation);
        return new ResponseEntity<>(prestationResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Prestation> updatePrestation(Long prestId, Prestation prestation) {
        prestation.setId(prestId);
        Prestation prestationResult = prestationService.savePrestation(prestation);
        return new ResponseEntity<>(prestationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Prestation> getPrestationById(Long prestId) {
        Prestation prestationResult = prestationService.findPrestationById(prestId)
                .orElseThrow(() -> new ResourceNotFoundException("Prestation " + prestId + "not found"));
        return new ResponseEntity<>(prestationResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Prestation>> getAllPrestations() {
        List<Prestation> prestationList = prestationService.findAllPrestations();
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Prestation>> getAllPrestationOrderDesc() {
        List<Prestation> prestationList = prestationService.findAllPrestationsOrderDesc();
        return new ResponseEntity<>(prestationList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getSumsOfPrestationInMonth() {
        return prestationService.sumTotalOfPrestationInMonth();
    }

    @Override
    public BigDecimal getSumsOfPrestationInYear() {
        return prestationService.sumTotalOfPrestationInYear();
    }

    @Override
    public ResponseEntity<?> deletePrestation(Long prestId) {
        prestationService.deletePrestation(prestId);
        return ResponseEntity.ok()
                .build();
    }
}
