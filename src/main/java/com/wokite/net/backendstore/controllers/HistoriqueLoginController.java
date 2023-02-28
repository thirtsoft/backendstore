package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.HistoriqueLoginApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueLogin;
import com.wokite.net.backendstore.services.HistoriqueLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueLoginController implements HistoriqueLoginApi {


    @Autowired
    private HistoriqueLoginService historiqueLoginService;

    @Override
    public ResponseEntity<HistoriqueLogin> createHistoriqueLogin(HistoriqueLogin historiqueLogin) {
        HistoriqueLogin historiqueLoginResult = historiqueLoginService.saveHistoriqueLogin(historiqueLogin);
        return new ResponseEntity<>(historiqueLoginResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueLogin> getHistoriqueLoginById(Long id) throws ResourceNotFoundException {
        HistoriqueLogin historiqueLoginResult = historiqueLoginService.findHistoriqueLoginById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historique Commande " + id + "not found"));
        return new ResponseEntity<>(historiqueLoginResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLogin() {
        List<HistoriqueLogin> historiqueLoginList = historiqueLoginService.findAllHistoriqueLogins();
        return new ResponseEntity<>(historiqueLoginList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLogin>> getAllHistoriqueLoginOrderDesc() {
        List<HistoriqueLogin> historiqueLoginList = historiqueLoginService.findAllHistoriqueLoginsOrderDesc();
        return new ResponseEntity<>(historiqueLoginList, HttpStatus.OK);
    }

    @Override
    public BigDecimal getNumberOfHistoriqueLogin() {
        return historiqueLoginService.countNumberOfHistoriqueLogins();
    }


    @Override
    public ResponseEntity<?> deleteHistoriqueLogin(Long id) {
        historiqueLoginService.deleteHistoriqueLogin(id);
        return ResponseEntity.ok()
                .build();
    }

}
