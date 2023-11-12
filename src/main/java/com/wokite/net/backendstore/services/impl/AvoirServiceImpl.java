package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.Avoir;
import com.wokite.net.backendstore.models.LigneAvoir;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.repository.AvoirRepository;
import com.wokite.net.backendstore.repository.LigneAvoirRepository;
import com.wokite.net.backendstore.services.AvoirService;
import com.wokite.net.backendstore.services.LigneAvoirService;
import com.wokite.net.backendstore.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AvoirServiceImpl implements AvoirService {

    Logger logger = LoggerFactory.getLogger(AvoirServiceImpl.class);


    private final AvoirRepository avoirRepository;

    private final LigneAvoirService ligneAvoirService;

    private final LigneAvoirRepository ligneAvoirRepository;

    private final ProductService productService;

    public AvoirServiceImpl(AvoirRepository avoirRepository,
                            LigneAvoirService ligneAvoirService,
                            LigneAvoirRepository ligneAvoirRepository,
                            ProductService productService) {
        this.avoirRepository = avoirRepository;
        this.ligneAvoirService = ligneAvoirService;
        this.ligneAvoirRepository = ligneAvoirRepository;
        this.productService = productService;
    }

    @Override
    public Avoir saveAvoir(Avoir avoir) {
        logger.info("Avoir {}", avoir);
        avoirRepository.save(avoir);
        List<LigneAvoir> ligneAvoirs = avoir.getLavoirs();
        double total = 0;
        for (LigneAvoir lavoir : ligneAvoirs) {
            lavoir.setAvoir(avoir);
            lavoir.setNumeroAvoir(avoir.getReference());
            ligneAvoirService.saveLigneAvoir(lavoir);
            Product product = productService.findProductById(lavoir.getProduct().getId()).get();
            lavoir.setPrixLigneAvoir(product.getPrixVente());
            lavoir.setActif(true);
            total += (lavoir.getQuantite() * product.getPrixVente());
        }
        avoir.setTotalAvoir(total);
        avoir.setStatus("valider");
        avoir.setDateAvoir(new Date());
        avoir.setActif(true);
        return avoirRepository.save(avoir);
    }

    @Override
    public Avoir updateAvoir(Long id, Avoir avoir) {
        if (!avoirRepository.existsById(id)) {
            throw new ResourceNotFoundException("Avoir that id is" + id + "not found");
        }
        Optional<Avoir> optionalAvoir = avoirRepository.findById(id);
        if (!optionalAvoir.isPresent()) {
            throw new ResourceNotFoundException("Avoir that id is" + optionalAvoir + "not found");
        }
        Avoir avoirResult = optionalAvoir.get();
        avoirResult.setReference(avoir.getReference());
        avoirResult.setFournisseur(avoir.getFournisseur());
        avoirResult.setTotalAvoir(avoir.getTotalAvoir());
        avoirResult.setStatus(avoir.getStatus());
        return avoirRepository.save(avoirResult);
    }

    @Override
    public Optional<Avoir> findAvoirById(Long id) {
        if (!avoirRepository.existsById(id)) {
            throw new ResourceNotFoundException("Avoir that id is" + id + "not found");
        }
        return avoirRepository.findById(id);
    }

    @Override
    public List<Avoir> findAllAvoirs() {
        return avoirRepository.findAll();
    }

    @Override
    public List<Avoir> findAllAvoirsOrderDesc() {
        return avoirRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Avoir> findAvoirsByFournisseurId(Long fourId) {
        return avoirRepository.findLitAvoirByFournisseurId(fourId);
    }

    @Override
    public void deleteAvoir(Long id) {
        Optional<Avoir> avoirInfo = avoirRepository.findById(id);
        if (avoirInfo.isPresent()) {
            Avoir avoir = avoirInfo.get();
            List<LigneAvoir> ligneAvoirs = avoir.getLavoirs();
            for (LigneAvoir ligneAvoir: ligneAvoirs) {
                ligneAvoir.setActif(false);
                ligneAvoirRepository.save(ligneAvoir);
            }
            avoir.setActif(false);
            avoirRepository.save(avoir);
        }
    }
}
