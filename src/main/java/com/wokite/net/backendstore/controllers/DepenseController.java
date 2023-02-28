package com.wokite.net.backendstore.controllers;

import com.wokite.net.backendstore.controllers.api.DepenseApi;
import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Depense;
import com.wokite.net.backendstore.models.HistoriqueDepense;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.security.service.UserPrinciple;
import com.wokite.net.backendstore.services.DepenseService;
import com.wokite.net.backendstore.services.HistoriqueDepenseService;
import com.wokite.net.backendstore.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DepenseController implements DepenseApi {

    @Autowired
    private DepenseService depenseService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private HistoriqueDepenseService historiqueDepenseService;

    @Override
    public ResponseEntity<Depense> createCharge(Depense depense) {
        if (depense.getCodeCharge() != null) {
            return new ResponseEntity<>(depense, HttpStatus.BAD_REQUEST);
        }
        Depense depenseResultant;
        String ch = "DEP#_" + Math.random() * 10;
        depense.setCodeCharge(ch);
        depenseResultant = depenseService.saveCharge(depense);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();

        HistoriqueDepense historiqueDepense = new HistoriqueDepense();
        historiqueDepense.setUtilisateur(utilisateur);
        historiqueDepense.setDepense(depenseResultant);
        historiqueDepense.setCreatedDate(new Date());
        historiqueDepense.setAction("AJOUT CHARGE");

        historiqueDepenseService.saveHistoriqueCharge(historiqueDepense);
*/
        return new ResponseEntity<>(depenseResultant, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Depense> updateCharge(Long catId, Depense depense) {
        depense.setId(catId);
        Depense depenseResultat = depenseService.updateCharge(catId, depense);

        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(authUser.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        */

        /*
        Utilisateur utilisateur = new Utilisateur();

        HistoriqueDepense historiqueDepense = new HistoriqueDepense();
        historiqueDepense.setUtilisateur(utilisateur);
        historiqueDepense.setDepense(depenseResultat);
        historiqueDepense.setCreatedDate(new Date());
        historiqueDepense.setAction("MODIFICATION CHARGE");

        historiqueDepenseService.saveHistoriqueCharge(historiqueDepense);
        */

        return new ResponseEntity<>(depenseResultat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Depense> getChargeById(Long id) {
        Depense depense = depenseService.findChargeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category that id is" + id + "not found"));
        return new ResponseEntity<>(depense, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Depense>> getAllCharges() {
        List<Depense> depenseList = depenseService.findAllCharges();
        return new ResponseEntity<>(depenseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Depense>> getAllChargesOrderDesc() {
        List<Depense> depenseList = depenseService.findAllChargesOrderDesc();
        return new ResponseEntity<>(depenseList, HttpStatus.OK);
    }

    @Override
    public BigDecimal sumTotalOfChargeInYear() {
        return depenseService.sumTotalOfChargeInYear();
    }

    @Override
    public List<?> sumTotalOfChargePeerMonth() {
        return depenseService.sumTotalOfChargePeerMonth();
    }

    @Override
    public ResponseEntity<?> deleteCharge(Long id) {
        depenseService.deleteCharge(id);
        return ResponseEntity.ok().build();
    }
}
