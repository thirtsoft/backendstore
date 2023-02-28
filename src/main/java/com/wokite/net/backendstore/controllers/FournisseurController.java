package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.FournisseurApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Fournisseur;
import com.wokite.net.backendstore.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class FournisseurController implements FournisseurApi {

    @Autowired
    private FournisseurService fournisseurService;

    @Override
    public ResponseEntity<Fournisseur> createFournisseur(Fournisseur fournisseur) {
        Fournisseur fournisseurResult = fournisseurService.saveFournisseur(fournisseur);
        return new ResponseEntity<>(fournisseurResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Fournisseur> updateFournisseur(Long catId, Fournisseur fournisseur) {
        fournisseur.setId(catId);
        Fournisseur fournisseurResult = fournisseurService.saveFournisseur(fournisseur);
        return new ResponseEntity<>(fournisseurResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Fournisseur> getFournisseurById(Long id) {
        Fournisseur fournisseurResult = fournisseurService.findFournisseurById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur that id is" + id + "not found"));
        return new ResponseEntity<>(fournisseurResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs() {
        List<Fournisseur> fournisseurList = fournisseurService.findAllFournisseurs();
        return new ResponseEntity<>(fournisseurList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Fournisseur>> getAllFournisseursOrderDesc() {
        List<Fournisseur> fournisseurList = fournisseurService.findAllFournisseursOrderDesc();
        return new ResponseEntity<>(fournisseurList, HttpStatus.OK);
    }

    @Override
    public Integer getNumberOfFournisseurs() {
        return fournisseurService.countNumberOfFournisseurs();
    }

    @Override
    public ResponseEntity<?> deleteFournisseur(Long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.ok().build();
    }
}
