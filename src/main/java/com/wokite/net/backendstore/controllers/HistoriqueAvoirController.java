package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueAvoirApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueAvoir;
import com.wokite.net.backendstore.services.HistoriqueAvoirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueAvoirController implements HistoriqueAvoirApi {

    @Autowired
    private HistoriqueAvoirService historiqueAvoirService;

    @Override
    public ResponseEntity<HistoriqueAvoir> createHistoriqueAvoir(HistoriqueAvoir historiqueAvoir) {
        HistoriqueAvoir historiqueAvoirResult = historiqueAvoirService.saveHistoriqueAvoir(historiqueAvoir);
        return new ResponseEntity<>(historiqueAvoirResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueAvoir> getHistoriqueAvoirById(Long id) throws ResourceNotFoundException {
        HistoriqueAvoir historiqueAvoirResult = historiqueAvoirService.findHistoriqueAvoirById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistoriqueAvoir that id is" + id + "not found"));
        return new ResponseEntity<>(historiqueAvoirResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAvoir>> getAllHistoriqueAvoirs() {
        List<HistoriqueAvoir> historiqueAvoirList = historiqueAvoirService.findAllHistoriqueAvoirs();
        return new ResponseEntity<>(historiqueAvoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueAvoir>> getAllHistoriqueAvoirOrderDesc() {
        List<HistoriqueAvoir> historiqueAvoirList = historiqueAvoirService.findAllHistoriqueAvoirsOrderDesc();
        return new ResponseEntity<>(historiqueAvoirList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueAvoirs() {
        return historiqueAvoirService.countNumberOfHistoriqueAvoirs();
    }

    @Override
    public ResponseEntity<?> deleteHistoriqueAvoir(Long id) {
        historiqueAvoirService.deleteHistoriqueAvoir(id);
        return ResponseEntity.ok()
                .build();
    }
}
