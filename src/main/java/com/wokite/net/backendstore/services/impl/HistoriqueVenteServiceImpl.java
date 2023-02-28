package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.HistoriqueVente;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.models.Vente;
import com.wokite.net.backendstore.repository.HistoriqueVenteRepository;
import com.wokite.net.backendstore.services.HistoriqueVenteService;
import com.wokite.net.backendstore.services.UtilisateurService;
import com.wokite.net.backendstore.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoriqueVenteServiceImpl implements HistoriqueVenteService {

    private final HistoriqueVenteRepository historiqueVenteRepository;

    private final UtilisateurService utilisateurService;

    private final VenteService venteService;

    @Autowired
    public HistoriqueVenteServiceImpl(HistoriqueVenteRepository historiqueVenteRepository,
                                      UtilisateurService utilisateurService, VenteService venteService) {
        this.historiqueVenteRepository = historiqueVenteRepository;
        this.utilisateurService = utilisateurService;
        this.venteService = venteService;
    }

    @Override
    public HistoriqueVente saveHistoriqueVente(HistoriqueVente historiqueVente) {
        return historiqueVenteRepository.save(historiqueVente);
    }

    @Override
    public HistoriqueVente saveHistoriqueVenteWithConnectedUserAndVenteID(HistoriqueVente historiqueVente,
                                                                          Long userId, Long venteId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurService.findUtilisateurById(userId);
        Utilisateur utilisateur = optionalUtilisateur.get();

        Optional<Vente> optionalVente = venteService.findVenteById(venteId);
        Vente vente = optionalVente.get();

        historiqueVente.setUtilisateur(utilisateur);
        historiqueVente.setVente(vente);
        historiqueVente.setCreatedDate(new Date());

        return historiqueVenteRepository.save(historiqueVente);
    }

    @Override
    public Optional<HistoriqueVente> findHistoriqueVenteById(Long id) {
        if (!historiqueVenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueVente that id is" + id + "not found");

        }
        return historiqueVenteRepository.findById(id);
    }

    @Override
    public List<HistoriqueVente> findAllHistoriqueVentes() {
        return historiqueVenteRepository.findAll();
    }

    @Override
    public List<HistoriqueVente> findAllHistoriqueVentesOrderDesc() {
        return historiqueVenteRepository.findByOrderByIdDesc();
    }

    @Override
    public BigDecimal countNumberOfHistoriqueVentes() {
        return historiqueVenteRepository.countNumberOfHistoriqueVentes();
    }

    @Override
    public void deleteHistoriqueVente(Long id) {
        if (!historiqueVenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueVente that id is" + id + "not found");

        }
        historiqueVenteRepository.deleteById(id);
    }
}
