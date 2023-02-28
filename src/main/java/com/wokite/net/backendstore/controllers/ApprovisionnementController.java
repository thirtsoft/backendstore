package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.ApprovisionnementApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Approvisionnement;
import com.wokite.net.backendstore.models.HistoriqueApprovisionnement;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.security.service.UserPrinciple;
import com.wokite.net.backendstore.services.ApprovisionnementService;
import com.wokite.net.backendstore.services.HistoriqueApprovisionnementService;
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
public class ApprovisionnementController implements ApprovisionnementApi {

    private final ApprovisionnementService approvisionnementService;

    private final UtilisateurService utilisateurService;

    private final HistoriqueApprovisionnementService historiqueApprovisionnementService;

    public ApprovisionnementController(ApprovisionnementService approvisionnementService, UtilisateurService utilisateurService, HistoriqueApprovisionnementService historiqueApprovisionnementService) {
        this.approvisionnementService = approvisionnementService;
        this.utilisateurService = utilisateurService;
        this.historiqueApprovisionnementService = historiqueApprovisionnementService;
    }


    @Override
    public ResponseEntity<Approvisionnement> createApprovisionnement(Approvisionnement approvisionnement) {

        approvisionnement.setCode(GeneratedNumber.generateCodeCommand());

        Approvisionnement approvisionnementResultat = approvisionnementService.saveApprovisionnement(approvisionnement);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();

        HistoriqueApprovisionnement historiqueApprovisionnement = new HistoriqueApprovisionnement();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(approvisionnementResultat);
        historiqueApprovisionnement.setAction("AJOUT ENTREE");
        historiqueApprovisionnement.setCreatedDate(new Date());

        historiqueApprovisionnementService.saveHistoriqueApprovisionnement(historiqueApprovisionnement);


         */
        return new ResponseEntity<>(approvisionnementResultat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Approvisionnement> updateApprovisionnement(Long ApproId, Approvisionnement approvisionnement) {
        approvisionnement.setId(ApproId);

        Approvisionnement approvisionnementResultat = approvisionnementService.saveApprovisionnement(approvisionnement);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        */
        /*
        Utilisateur utilisateur = new Utilisateur();

        HistoriqueApprovisionnement historiqueApprovisionnement = new HistoriqueApprovisionnement();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(approvisionnementResultat);
        historiqueApprovisionnement.setAction("MODIFICATION ENTREE");
        historiqueApprovisionnement.setCreatedDate(new Date());

        historiqueApprovisionnementService.saveHistoriqueApprovisionnement(historiqueApprovisionnement);


         */
        return new ResponseEntity<>(approvisionnementResultat, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Approvisionnement> getApprovisionnementById(Long id) throws ResourceNotFoundException {
        Approvisionnement approvisionnement = approvisionnementService.findApprovisionnementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Approvisionnement that id is" + id + "not found"));
        return new ResponseEntity<>(approvisionnement, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateStatusAppro(String status, String id) {
        Approvisionnement newApprovisionnement = approvisionnementService.updateStatusAppro(status, id);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        */

        /*
        Utilisateur utilisateur = new Utilisateur();

        HistoriqueApprovisionnement historiqueApprovisionnement = new HistoriqueApprovisionnement();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(newApprovisionnement);
        historiqueApprovisionnement.setAction("MODIFICATION STATUS ENTREE");
        historiqueApprovisionnement.setCreatedDate(new Date());

        historiqueApprovisionnementService.saveHistoriqueApprovisionnement(historiqueApprovisionnement);


         */
        return new ResponseEntity<>(newApprovisionnement, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateMontantAvanceAppro(double montantAvance, String id) {
        Approvisionnement newApprovisionnement = approvisionnementService.updateMontantAvanceAppro(montantAvance, id);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();*/

        /*
        Utilisateur utilisateur = new Utilisateur();

        HistoriqueApprovisionnement historiqueApprovisionnement = new HistoriqueApprovisionnement();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(newApprovisionnement);
        historiqueApprovisionnement.setAction("MODIFICATION MONTANT AVANCE ENTREE");
        historiqueApprovisionnement.setCreatedDate(new Date());

        historiqueApprovisionnementService.saveHistoriqueApprovisionnement(historiqueApprovisionnement);


         */
        return new ResponseEntity<>(newApprovisionnement, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Approvisionnement>> getAllApprovisionnements() {
        List<Approvisionnement> approvisionnementList = approvisionnementService.findAllApprovisionnements();
        return new ResponseEntity<>(approvisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Approvisionnement>> getAllApprovisionnementsOrderDesc() {
        List<Approvisionnement> approvisionnementList = approvisionnementService.findAllApprovisionnementsOrderDesc();
        return new ResponseEntity<>(approvisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Approvisionnement>> getApprovisionnementsOfLatest3Months() {
        List<Approvisionnement> approvisionnementList = approvisionnementService.findListApprovisionnementOf3LatestMonth();
        return new ResponseEntity<>(approvisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Approvisionnement>> getTop500OApprovisionnementOrderByIdDesc() {
        List<Approvisionnement> approvisionnementList = approvisionnementService.findTop500OApprovisionnementOrderByIdDesc();
        return new ResponseEntity<>(approvisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Approvisionnement>> getListApprovisionnementByFournisseurId(Long fourId) {
        List<Approvisionnement> approvisionnementList = approvisionnementService.findListApprovisionnementByFournisseurId(fourId);
        return new ResponseEntity<>(approvisionnementList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAppro(Long id) {
        Optional<Approvisionnement> optionalApprovisionnement = approvisionnementService.findApprovisionnementById(id);
        Approvisionnement approvisionnement = optionalApprovisionnement.get();

        approvisionnementService.deleteAppro(id);
/*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get(); */

        Utilisateur utilisateur = new Utilisateur();

        HistoriqueApprovisionnement historiqueApprovisionnement = new HistoriqueApprovisionnement();

        historiqueApprovisionnement.setUtilisateur(utilisateur);
        historiqueApprovisionnement.setApprovisionnement(approvisionnement);
        historiqueApprovisionnement.setAction("SUPPRESSION ENTREE");
        historiqueApprovisionnement.setCreatedDate(new Date());

        historiqueApprovisionnementService.saveHistoriqueApprovisionnement(historiqueApprovisionnement);

        return ResponseEntity.ok().build();

    }
}
