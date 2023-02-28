package com.wokite.net.backendstore;

import com.wokite.net.backendstore.models.*;
import com.wokite.net.backendstore.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendstoreApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final FournisseurRepository fournisseurRepository;
    private final CommandeRepository commandeRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final VenteRepository venteRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final UtilisateurRepository utilisateurRepository;


    public static void main(String[] args) {
        SpringApplication.run(BackendstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*

        Category category = categoryRepository.save(new Category(1L, "CAT01", "Category01"));
        Category category02 = categoryRepository.save(new Category(2L, "CAT02", "Category02"));
        Category category03 = categoryRepository.save(new Category(3L, "CAT03", "Category03"));
        Category category04 = categoryRepository.save(new Category(4L, "CAT04", "Category04"));
        Category category05 = categoryRepository.save(new Category(5L, "CAT005", "Category05"));

        SubCategory SubCategory = subCategoryRepository.save(new SubCategory(1L, "SUBCAT01", "Category01", category));
        SubCategory SubCategory02 = subCategoryRepository.save(new SubCategory(2L, "CAT02", "Category02", category02));
        SubCategory SubCategory03 = subCategoryRepository.save(new SubCategory(3L, "CAT03", "Category03", category03));
        SubCategory SubCategory04 = subCategoryRepository.save(new SubCategory(4L, "CAT04", "Category04", category04));
        SubCategory SubCategory05 = subCategoryRepository.save(new SubCategory(5L, "CAT005", "Category05", category05));


        Product product = productRepository.save(new Product(1L, "123456789", "Product01", 1200.0, 2000.0, 12, SubCategory));
        Product product02 = productRepository.save(new Product(2L, "459789", "Product02", 3200.0, 5000.0, 6, SubCategory02));
        Product product03 = productRepository.save(new Product(3L, "678912346", "Product03", 6000.0, 7000.0, 2, SubCategory03));
        Product product04 = productRepository.save(new Product(4L, "5789985", "Product04", 6700.0, 8000.0, 8, SubCategory04));
        Product product05 = productRepository.save(new Product(5L, "80987654", "Product05", 8500.0, 10000.0, 10, SubCategory05));
        Product product06 = productRepository.save(new Product(6L, "12345678915", "Product06", 8000.0, 9000.0, 16, SubCategory02));

		Fournisseur fournisseur = fournisseurRepository.save(new Fournisseur(1L, "Wokite", "2023A", "779440310", "wokite.net"));
		Fournisseur fournisseur02 = fournisseurRepository.save(new Fournisseur(2L, "FFF02", "2F023A", "77940310", "wokite02.net"));
		Fournisseur fournisseur03 = fournisseurRepository.save(new Fournisseur(3L, "FFF03", "F2F3A", "77440310", "wokite03.net"));
		Fournisseur fournisseur04 = fournisseurRepository.save(new Fournisseur(4L, "FFF04", "20FA", "79440310", "wokite04.net"));
		Fournisseur fournisseur05 = fournisseurRepository.save(new Fournisseur(5L, "FFF05", "20FFA", "77944031", "wokite05.net"));
		Fournisseur fournisseur06 = fournisseurRepository.save(new Fournisseur(6L, "Wokite6", "2023A6", "7669440310", "wokite06.net"));
		Fournisseur fournisseur07 = fournisseurRepository.save(new Fournisseur(7L, "FFF027", "2F023A7", "777740310", "wokite07.net"));
		Fournisseur fournisseur08 = fournisseurRepository.save(new Fournisseur(8L, "FFF038", "F2F3A8", "88440310", "wokite08.net"));
		Fournisseur fournisseur09 = fournisseurRepository.save(new Fournisseur(9L, "FFF049", "20FA9", "79940310", "wokite09.net"));
		Fournisseur fournisseur10 = fournisseurRepository.save(new Fournisseur(10L, "FFF0510", "20FFA10", "107944031", "wokite10.net"));
		Fournisseur fournisseur11 = fournisseurRepository.save(new Fournisseur(11L, "Wokite11", "2023A11", "1179440310", "wokite11.net"));
		Fournisseur fournisseur12 = fournisseurRepository.save(new Fournisseur(12L, "FFF012", "2F023A12", "127940310", "wokite12.net"));
		Fournisseur fournisseur13 = fournisseurRepository.save(new Fournisseur(13L, "FFF013", "F2F3A13", "137440310", "wokite13.net"));
		Fournisseur fournisseur14 = fournisseurRepository.save(new Fournisseur(14L, "FFF014", "20FA14", "1479440310", "wokite14.net"));
		Fournisseur fournisseur15 = fournisseurRepository.save(new Fournisseur(15L, "FFF015", "20FFA15", "1577944031", "wokite15.net"));

        Client client = clientRepository.save(new Client(1L, "Client", "778643217", "client@gmail.com"));
        Client client02 = clientRepository.save(new Client(2L, "Client02", "777643217", "client02@gmail.com"));
        Client client03 = clientRepository.save(new Client(3L, "Client03", "768643217", "client03@gmail.com"));
        Client client04 = clientRepository.save(new Client(4L, "Client04", "788643217", "client04@gmail.com"));
        Client client05 = clientRepository.save(new Client(5L, "Client05", "778543217", "client05@gmail.com"));

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setActive(true);
        utilisateur.setName("Mawdo");
        utilisateur.setUsername("mawdo");
        utilisateurRepository.save(utilisateur);

        Utilisateur vendor = new Utilisateur();
        vendor.setActive(true);
        vendor.setName("Alpha");
        vendor.setUsername("alpha");
        utilisateurRepository.save(vendor);

        Commande commande = new Commande(1452L, 250000.5, 10000.5, "Cash", "VALIDEE", new Date());
        commande.setClient(client);
        commande.setUtilisateur(utilisateur);
        commandeRepository.save(commande);

        Commande commande2 = new Commande(14522L, 220000.5, 20000.5, "Wave", "PAYEE", new Date());
        commande2.setClient(client02);
        commande2.setUtilisateur(vendor);
        commandeRepository.save(commande2);

        Commande commande3 = new Commande(1453L, 230000.5, 30000.5, "OrangeMoney", "VALIDEE", new Date());
        commande3.setClient(client03);
        commande3.setUtilisateur(utilisateur);
        commandeRepository.save(commande3);

        Commande commande4 = new Commande(1454L, 240000.5, 40000.5, "FreeMoney", "ENCOURS", new Date());
        commande4.setClient(client04);
        commande4.setUtilisateur(vendor);
        commandeRepository.save(commande4);

        Commande commande5 = new Commande(1455L, 250000.5, 50000.5, "Credit card", "VALIDEE", new Date());
        commande5.setClient(client04);
        commande5.setUtilisateur(utilisateur);
        commandeRepository.save(commande5);

        LigneCommande ligneCommande = new LigneCommande(1L, 5, 15000.5, 15000.0, product);
        ligneCommande.setCommande(commande);
        ligneCommandeRepository.save(ligneCommande);
        LigneCommande ligneCommande1 = new LigneCommande(2L, 11, 17000.5, 11000.0, product02);
        ligneCommande1.setCommande(commande);
        ligneCommandeRepository.save(ligneCommande1);

        LigneCommande ligneCommande2 = new LigneCommande(3L, 8, 18000.5, 11000.0, product03);
        ligneCommande2.setCommande(commande2);
        ligneCommandeRepository.save(ligneCommande2);

        LigneCommande ligneCommande3 = new LigneCommande(4L, 6, 16000.5, 13000.0, product04);
        ligneCommande3.setCommande(commande3);
        ligneCommandeRepository.save(ligneCommande2);

        LigneCommande ligneCommande4 = new LigneCommande(5L, 4, 19000.5, 75000.0, product05);
        ligneCommande4.setCommande(commande4);
        ligneCommandeRepository.save(ligneCommande4);

        LigneCommande ligneCommande5 = new LigneCommande(6L, 2, 2000.5, 17000.0, product06);
        ligneCommande5.setCommande(commande5);
        ligneCommandeRepository.save(ligneCommande5);

        LigneCommande ligneCommande7 = new LigneCommande(7L, 7, 7000.5, 77000.0, product05);
        ligneCommande7.setCommande(commande5);
        ligneCommandeRepository.save(ligneCommande7);

        Vente vente1 = new Vente();
        vente1.setNumeroVente(1300L);
		vente1.setTotalVente(15000.5);
		vente1.setStatus("ENCOURS");
		vente1.setTypeReglement("Wave");
		vente1.setMontantReglement(15000.5);
		vente1.setUtilisateur(utilisateur);
        venteRepository.save(vente1);

        Vente vente2 = new Vente();
		vente2.setNumeroVente(1400L);
		vente2.setTotalVente(15000.5);
		vente2.setStatus("PAYEE");
		vente2.setTypeReglement("OrangeMoney");
		vente2.setMontantReglement(15000.5);
		vente2.setUtilisateur(utilisateur);
        venteRepository.save(vente2);
        Vente vente3 = new Vente();
		vente3.setNumeroVente(1500L);
		vente3.setTotalVente(15000.5);
		vente3.setStatus("PAYEE");
		vente3.setTypeReglement("OrangeMoney");
		vente3.setMontantReglement(15000.5);
		vente3.setUtilisateur(vendor);
        venteRepository.save(vente3);

		LigneVente ligneVente1 = new LigneVente();
		ligneVente1.setVente(vente1);ligneVente1.setPrixVente(1200.0);ligneVente1.setQuantite(2);ligneVente1.setProduct(product02);
		ligneVenteRepository.save(ligneVente1);

		LigneVente ligneVente2 = new LigneVente();
		ligneVente2.setVente(vente2);ligneVente2.setPrixVente(1200.0);ligneVente2.setQuantite(2);ligneVente2.setProduct(product03);
		ligneVenteRepository.save(ligneVente2);

		LigneVente ligneVente3 = new LigneVente();
		ligneVente3.setVente(vente3);ligneVente3.setPrixVente(1200.0);ligneVente3.setQuantite(2);ligneVente3.setProduct(product04);
		ligneVenteRepository.save(ligneVente3);

        */




    }
}
