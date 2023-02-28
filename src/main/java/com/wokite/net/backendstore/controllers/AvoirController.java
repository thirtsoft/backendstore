package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.AvoirApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Avoir;
import com.wokite.net.backendstore.models.HistoriqueAvoir;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.security.service.UserPrinciple;
import com.wokite.net.backendstore.services.AvoirService;
import com.wokite.net.backendstore.services.HistoriqueAvoirService;
import com.wokite.net.backendstore.services.UtilisateurService;
import com.wokite.net.backendstore.utils.GeneratedNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class AvoirController implements AvoirApi {


    private final AvoirService avoirService;

    private final HistoriqueAvoirService historiqueAvoirService;

    private final UtilisateurService utilisateurService;

    public AvoirController(AvoirService avoirService, HistoriqueAvoirService historiqueAvoirService, UtilisateurService utilisateurService) {
        this.avoirService = avoirService;
        this.historiqueAvoirService = historiqueAvoirService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<Avoir> createAvoir(Avoir avoir) {
        avoir.setReference(GeneratedNumber.generateCodeCommand());
        Avoir avoidResult = avoirService.saveAvoir(avoir);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();

        HistoriqueAvoir historiqueAvoir = new HistoriqueAvoir();

        historiqueAvoir.setUtilisateur(utilisateur);
        historiqueAvoir.setAvoir(avoidResult);
        historiqueAvoir.setAction("AJOUT AVOIR");
        historiqueAvoir.setCreatedDate(new Date());

        historiqueAvoirService.saveHistoriqueAvoir(historiqueAvoir);

        return new ResponseEntity<>(avoidResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Avoir> updateAvoir(Long id, Avoir avoir) {
        Avoir avoirResultat;

        avoir.setId(id);

        avoirResultat = avoirService.saveAvoir(avoir);
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();*/

        Utilisateur utilisateur = new Utilisateur();

        HistoriqueAvoir historiqueAvoir = new HistoriqueAvoir();

        historiqueAvoir.setUtilisateur(utilisateur);
        historiqueAvoir.setAvoir(avoirResultat);
        historiqueAvoir.setAction("MODIFICATION AVOIR");
        historiqueAvoir.setCreatedDate(new Date());

        historiqueAvoirService.saveHistoriqueAvoir(historiqueAvoir);

        return new ResponseEntity<>(avoirResultat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Avoir> getAvoirById(Long id) throws ResourceNotFoundException {
        Avoir avoir = avoirService.findAvoirById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avoir Not found"));
        return new ResponseEntity<>(avoir, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Avoir>> getAllAvoirs() {
        List<Avoir> avoirList = avoirService.findAllAvoirs();
        return new ResponseEntity<>(avoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Avoir>> getAllAvoirOrderDesc() {
        List<Avoir> avoirList = avoirService.findAllAvoirs();
        return new ResponseEntity<>(avoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Avoir>> getListOfAvoirByFournisseurId(Long fourId) {
        List<Avoir> avoirList = avoirService.findAvoirsByFournisseurId(fourId);
        return new ResponseEntity<>(avoirList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteAvoir(Long id) {
        Avoir avoirResultat;

        avoirResultat = avoirService.findAvoirById(id).get();
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        */
        Utilisateur utilisateur = new Utilisateur();

        HistoriqueAvoir historiqueAvoir = new HistoriqueAvoir();

        historiqueAvoir.setUtilisateur(utilisateur);
        historiqueAvoir.setAvoir(avoirResultat);
        historiqueAvoir.setAction("SUPPRESSION AVOIR");
        historiqueAvoir.setCreatedDate(new Date());

        historiqueAvoirService.saveHistoriqueAvoir(historiqueAvoir);

        avoirService.deleteAvoir(id);

        return ResponseEntity.ok().build();
    }
}
