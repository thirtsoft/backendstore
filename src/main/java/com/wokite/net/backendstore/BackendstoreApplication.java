package com.wokite.net.backendstore;

import com.wokite.net.backendstore.enums.RoleName;
import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.*;
import com.wokite.net.backendstore.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



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
    private final RoleRepository roleRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    /*
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    */


    public static void main(String[] args) {
        SpringApplication.run(BackendstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*
        Category category = categoryRepository.save(new Category(1L, "CAT01", "Ordinateur portable"));
        Category category02 = categoryRepository.save(new Category(2L, "CAT02", "Téléphone portable"));
        Category category03 = categoryRepository.save(new Category(3L, "CAT06", "Boisson"));
        Category category04 = categoryRepository.save(new Category(4L, "CAT07", "Eau minérale"));
        Category category05 = categoryRepository.save(new Category(5L, "CAT09", "Nescafé"));

        SubCategory SubCategory = subCategoryRepository.save(new SubCategory(1L, "SUBCAT01", "Ordinateur portable HP", category));
        SubCategory SubCategory02 = subCategoryRepository.save(new SubCategory(2L, "SUBCAT02", "Ordinateur portable DELL", category));
        SubCategory SubCategory03 = subCategoryRepository.save(new SubCategory(3L, "SUBCAT03", "Téléphone portable IPhone", category02));
        SubCategory SubCategory04 = subCategoryRepository.save(new SubCategory(4L, "SUBCAT04", "Téléphone portable Samsung", category02));
        SubCategory SubCategory05 = subCategoryRepository.save(new SubCategory(5L, "SUBCAT05", "Boisson Gazeuse", category03));
        SubCategory SubCategory06 = subCategoryRepository.save(new SubCategory(6L, "SUBCAT06", "Bierre", category03));
        SubCategory SubCategory07 = subCategoryRepository.save(new SubCategory(7L, "SUBCAT07", "Casamançaise", category04));
        SubCategory SubCategory08 = subCategoryRepository.save(new SubCategory(8L, "SUBCAT08", "Kirène", category04));

        SubCategory SubCategory09 = subCategoryRepository.save(new SubCategory(9L, "SUBCAT09", "Gold Intenso", category05));
        SubCategory SubCategory010 = subCategoryRepository.save(new SubCategory(10L, "SUBCAT010", "Nescafé", category05));

        Product product = productRepository.save(new Product(1L, "123456789", "HP elitebook core i7 vPro", 760000.0, 790000.0, 12, SubCategory));
        Product product02 = productRepository.save(new Product(2L, "459789", "HP Probook core i5", 320000.0, 500000.0, 6, SubCategory));
        Product product03 = productRepository.save(new Product(3L, "678912346", "Dell Promodel core i7", 540000.0, 720000.0, 2, SubCategory02));
        Product product04 = productRepository.save(new Product(4L, "810987654", "Iphone 14s", 460000.0, 660000.0, 10, SubCategory03));
        Product product05 = productRepository.save(new Product(5L, "5789985", "Iphone 12s", 320000.0, 530000.0, 8, SubCategory03));
        Product product06 = productRepository.save(new Product(6L, "12345678915", "Samsung A23", 11000.0, 125000.0, 16, SubCategory04));
        Product product07 = productRepository.save(new Product(7L, "012345678915", "Samsung A32", 130000.0, 145000.0, 16, SubCategory04));
        Product product08 = productRepository.save(new Product(8L, "80987654", "Coca cola", 680.0, 800.0, 10, SubCategory05));
        Product product09 = productRepository.save(new Product(9L, "51789985", "Fanta", 680.0, 800.0, 8, SubCategory05));
        Product product010 = productRepository.save(new Product(10L, "112345678915", "Malta", 890.0, 1250.0, 16, SubCategory06));
        Product product011 = productRepository.save(new Product(11L, "22345678915", "Malta", 890.0, 1250.0, 16, SubCategory06));
        Product product012 = productRepository.save(new Product(12L, "52789985", "Casamançaise 1L", 360.0, 500.0, 8, SubCategory07));
        Product product013 = productRepository.save(new Product(13L, "42345678915", "Casamançaise 5L", 760.0, 1100.0, 16, SubCategory07));
        Product product014 = productRepository.save(new Product(14L, "62345678915", "Kirène 1L", 390.0, 500.0, 16, SubCategory08));
        Product product015 = productRepository.save(new Product(15L, "53789985", "Kirène 5L", 820.0, 1100.0, 8, SubCategory08));
        Product product016 = productRepository.save(new Product(16L, "72345678915", "Gold Intenso PM", 1800.0, 2500.0, 16, SubCategory09));
        Product product017 = productRepository.save(new Product(17L, "82345678915", "Gold Intenso GM", 5500.0, 6000.0, 16, SubCategory09));
        Product product018 = productRepository.save(new Product(18L, "92345678915", "Nescafé PM", 680.0, 900.0, 16, SubCategory010));
        Product product019 = productRepository.save(new Product(19L, "102345678915", "Nescafé GM", 1100.0, 1900.0, 16, SubCategory010));

        Fournisseur fournisseur = fournisseurRepository.save(new Fournisseur(1L, "Wokite", "12345678-BOA", "779440310", "contact@wokite.net"));
		Fournisseur fournisseur02 = fournisseurRepository.save(new Fournisseur(2L, "SAT", "987654321-BNDE", "77940310", "support@sat.com"));
		Fournisseur fournisseur03 = fournisseurRepository.save(new Fournisseur(3L, "FIIT", "23456789-CBEAO", "77440310", "contact@fiit.com"));
		Fournisseur fournisseur04 = fournisseurRepository.save(new Fournisseur(4L, "Sunuchauffeur", "12346543-ECOBANK", "79440310", "contact@sunuchauffeur.com"));
		Fournisseur fournisseur05 = fournisseurRepository.save(new Fournisseur(5L, "Fournisseur05", "20FFA", "77944031", "fournisseur05.net"));
		Fournisseur fournisseur06 = fournisseurRepository.save(new Fournisseur(6L, "Fournisseur06", "2023A6", "7669440310", "fournisseur06.net"));
		Fournisseur fournisseur07 = fournisseurRepository.save(new Fournisseur(7L, "Fournisseur07", "2F023A7", "777740310", "fournisseur07.net"));
		Fournisseur fournisseur08 = fournisseurRepository.save(new Fournisseur(8L, "Fournisseur08", "F2F3A8", "88440310", "fournisseur08.net"));
		Fournisseur fournisseur09 = fournisseurRepository.save(new Fournisseur(9L, "Fournisseur09", "20FA9", "79940310", "fournisseur09.net"));
		Fournisseur fournisseur10 = fournisseurRepository.save(new Fournisseur(10L, "Fournisseur010", "20FFA10", "107944031", "fournisseur010.net"));

        Client client = clientRepository.save(new Client(1L, "Wokite", "778643217", "contact@wokite.net"));
        Client client02 = clientRepository.save(new Client(2L, "Sunuchauffeur", "777643217", "contact@sunuchauffeur.com"));
        Client client03 = clientRepository.save(new Client(3L, "ParfumD&P", "768643217", "contact@parfumdp.sn"));
        Client client04 = clientRepository.save(new Client(4L, "Funteks", "788643217", "support@funteks.com"));
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



        Role vendorRole = roleRepository.save(new Role(RoleName.ROLE_VENDEUR));
        Role gerantRole = roleRepository.save(new Role(RoleName.ROLE_GERANT));
        Role managerRole = roleRepository.save(new Role(RoleName.ROLE_MANAGER));
        Role adminRole = roleRepository.save(new Role(RoleName.ROLE_ADMIN));




        Role vendorRole = roleRepository.save(new Role(RoleName.ROLE_VENDEUR));
        Role gerantRole = roleRepository.save(new Role(RoleName.ROLE_GERANT));
        Role managerRole = roleRepository.save(new Role(RoleName.ROLE_MANAGER));
        Role adminRole = roleRepository.save(new Role(RoleName.ROLE_ADMIN));


        Utilisateur admin = new Utilisateur();
        admin.setUsername("admin");
        admin.setName("Administrator");
        admin.setEmail("admin@gmail.com");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        utilisateurRepository.save(admin);
        utilisateurService.addRoleToUser("admin", RoleName.ROLE_ADMIN);

        Utilisateur manager = new Utilisateur();
        manager.setUsername("manager");
        manager.setName("Manageur");
        manager.setEmail("manager@gmail.com");
        manager.setActive(true);
        manager.setPassword(bCryptPasswordEncoder.encode("manager"));
        utilisateurRepository.save(manager);
        utilisateurService.addRoleToUser("manager", RoleName.ROLE_MANAGER);

        Utilisateur gerant = new Utilisateur();
        gerant.setUsername("gerant");
        gerant.setName("Gerant");
        gerant.setEmail("gerant@gmail.com");
        gerant.setActive(true);
        gerant.setPassword(bCryptPasswordEncoder.encode("gerant"));
        utilisateurRepository.save(gerant);
        utilisateurService.addRoleToUser("gerant", RoleName.ROLE_GERANT);

        Utilisateur vendeur = new Utilisateur();
        vendeur.setUsername("vendeur");
        vendeur.setName("Vendeur");
        vendeur.setEmail("vendeur@gmail.com");
        vendeur.setActive(true);
        vendeur.setPassword(bCryptPasswordEncoder.encode("vendeur"));
        utilisateurRepository.save(vendeur);
        utilisateurService.addRoleToUser("vendeur", RoleName.ROLE_VENDEUR);


 */

    }
}
