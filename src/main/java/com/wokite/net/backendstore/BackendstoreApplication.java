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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@RequiredArgsConstructor
public class BackendstoreApplication implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(BackendstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*

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
