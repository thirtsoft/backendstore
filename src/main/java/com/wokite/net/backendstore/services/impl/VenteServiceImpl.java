package com.wokite.net.backendstore.services.impl;

import com.wokite.net.backendstore.exceptions.ResourceNotFoundException;
import com.wokite.net.backendstore.models.LigneVente;
import com.wokite.net.backendstore.models.Product;
import com.wokite.net.backendstore.models.Vente;
import com.wokite.net.backendstore.repository.LigneVenteRepository;
import com.wokite.net.backendstore.repository.VenteRepository;
import com.wokite.net.backendstore.services.LigneVenteService;
import com.wokite.net.backendstore.services.ProductService;
import com.wokite.net.backendstore.services.VenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;

    private final LigneVenteService ligneVenteService;

    private final LigneVenteRepository ligneVenteRepository;

    private final ProductService productService;

    public VenteServiceImpl(VenteRepository venteRepository,
                            LigneVenteService ligneVenteService,
                            LigneVenteRepository ligneVenteRepository,
                            ProductService productService) {
        this.venteRepository = venteRepository;
        this.ligneVenteService = ligneVenteService;
        this.ligneVenteRepository = ligneVenteRepository;
        this.productService = productService;
    }


    @Override
    public Vente saveVente(Vente vente) {
        System.out.println("Initial Numero Vente " + vente.getNumeroVente());
        List<LigneVente> ligneVenteList = vente.getLigneVentes();
        if (ligneVenteList == null || ligneVenteList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins ajouter un produit");
        }
        for (LigneVente ligneVente : ligneVenteList) {
            Product produitInitial = productService.findProductById(ligneVente.getProduct().getId()).get();
            if (ligneVente.getQuantite() > produitInitial.getQtestock()) {
                throw new IllegalArgumentException("La Quantité de stock du produit est insuffusante");
            }
        }
        venteRepository.save(vente);
        List<LigneVente> ligneVenteListProducts = vente.getLigneVentes();
        double total = 0;
        for (LigneVente ligneVente : ligneVenteListProducts) {
            ligneVente.setVente(vente);
            ligneVente.setNumeroVente(vente.getNumeroVente());
            ligneVente.setActif(true);
            ligneVenteService.saveLigneVente(ligneVente);
            Product product = productService.findProductById(ligneVente.getProduct().getId()).get();
            if (product != null) {
                product.setQtestock(product.getQtestock() - ligneVente.getQuantite());
                productService.updateProduct(product.getId(), product);
            }
            ligneVente.setPrixVente(product.getPrixDetail());
            total += (ligneVente.getQuantite() * product.getPrixDetail());
        }
        vente.setTotalVente(total);
        vente.setStatus("VALIDEE");
        vente.setDateVente(new Date());
        vente.setUtilisateur(vente.getUtilisateur());
        System.out.println("Fin Numero Vente " + vente.getNumeroVente());
        vente.setActif(true);
        return venteRepository.save(vente);
    }

    @Override
    public Vente saveVenteWithBarcode(Vente vente) {
        List<LigneVente> ligneVenteList = vente.getLigneVentes();
        if (ligneVenteList == null || ligneVenteList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins ajouter un produit");
        }
        venteRepository.save(vente);
        System.out.println("Milieu Numero Vente " + vente.getNumeroVente());
        List<LigneVente> ligneVenteListResult = vente.getLigneVentes();
        double total = 0;
        for (LigneVente ligneVente : ligneVenteListResult) {
            ligneVente.setVente(vente);
            ligneVente.setNumeroVente(vente.getNumeroVente());
            ligneVente.setActif(true);
            ligneVenteService.saveLigneVente(ligneVente);
            Product product = productService.findProductById(ligneVente.getProduct().getId()).get();
            if (product != null) {
                product.setQtestock(product.getQtestock() - ligneVente.getQuantite());
                productService.updateProduct(product.getId(), product);
            }
            ligneVente.setPrixVente(product.getPrixDetail());
            total += (ligneVente.getQuantite() * product.getPrixDetail());
        }

        vente.setTotalVente(total);
        vente.setStatus("valider");
        vente.setDateVente(new Date());
        vente.setUtilisateur(vente.getUtilisateur());
        vente.setActif(true);
        return venteRepository.save(vente);
    }

    @Override
    public Vente updateVente(Long venteId, Vente vente) {
        if (!venteRepository.existsById(venteId)) {
            throw new ResourceNotFoundException("Vente N° " + venteId + "not found");
        }
        Optional<Vente> venteProd = venteRepository.findById(venteId);
        if (!venteProd.isPresent()) {
            throw new ResourceNotFoundException("Vente N ° " + venteId + "not found");
        }
        Vente venteResultat = venteProd.get();
        venteResultat.setNumeroVente(vente.getNumeroVente());
        venteResultat.setTotalVente(vente.getTotalVente());
        venteResultat.setDateVente(new Date());
        return venteRepository.save(venteResultat);
    }

    @Override
    public Optional<Vente> findVenteById(Long venteId) {
        if (!venteRepository.existsById(venteId)) {
            throw new ResourceNotFoundException("Vente Not found");
        }
        return venteRepository.findById(venteId);
    }

    @Override
    public Vente findVenteByNumeroVente(Long numeroVente) {
        return venteRepository.findByNumeroVente(numeroVente);
    }

    @Override
    public List<Vente> findAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public List<Vente> findAllVentesOrderDesc() {
        return venteRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Vente> findListVenteOf3LatestMonth() {
        return venteRepository.findListVenteOf3LatestMonth();
    }

    @Override
    public List<Vente> findTop500OVenteOrderByIdDesc() {
        return venteRepository.findTop500ByOrderByIdDesc();
    }

    @Override
    public List<Vente> findVenteWithParticularDayAndMonth() {
        return venteRepository.findVenteWithParticularDayAndMonth();
    }

    @Override
    public List<Vente> findListVenteByEmployeId(Long empId) {
        return venteRepository.findAllVenteByEmployeId(empId);
    }

    @Override
    public Integer countNumberOfVenteInDay() {
        return venteRepository.countNumberOfVenteByDay();
    }

    @Override
    public int getNombreVentes(Date d1, Date d2) {
        return 0;
    }

    @Override
    public Long getNumberOfVente() {
        return venteRepository.count();
    }

    @Override
    public long generateNumeroVente() {
        return 0;
    }

    @Override
    public BigDecimal countSumsOfVentess() {
        return null;
    }

    @Override
    public BigDecimal sumTotalOfVenteInDay() {
        return venteRepository.sumTotalOfVenteByDay();
    }

    @Override
    public BigDecimal sumTotalOfVentesInMonth() {
        return venteRepository.sumTotalOfVentesByMonth();
    }

    @Override
    public BigDecimal sumTotalOfVentesInYear() {
        return venteRepository.sumTotalOfVentesByYear();
    }

    @Override
    public List<?> countNumberTotalOfVentePeerMonth() {
        return venteRepository.countNumberOfVenteByMonth();
    }

    @Override
    public List<?> sumTotalOfVentePeerMonth() {
        return venteRepository.sumTotalOfVenteByMonth();
    }

    @Override
    public List<?> sumTotalOfVentePeerYears() {
        return venteRepository.sumTotalOfVenteByYears();
    }

    @Override
    public ResponseEntity<Object> deleteVenteClient(Long id) {
        if (!venteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vente Not found");
        }
        venteRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteVente(Long id) {
        Optional<Vente> venteInfo = venteRepository.findById(id);
        if (venteInfo.isPresent()) {
            Vente vente = venteInfo.get();
            List<LigneVente> ligneVenteList = vente.getLigneVentes();
            for (LigneVente ligneVente : ligneVenteList) {
                ligneVente.setActif(false);
                ligneVenteRepository.save(ligneVente);
            }
            vente.setActif(false);
            venteRepository.save(vente);
        }
    }
}
