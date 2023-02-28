package com.wokite.net.backendstore.controllers;


import com.wokite.net.backendstore.controllers.api.AuthApi;
import com.wokite.net.backendstore.enums.RoleName;
import com.wokite.net.backendstore.message.request.LoginForm;
import com.wokite.net.backendstore.message.request.SignUpForm;
import com.wokite.net.backendstore.message.response.JwtResponse;
import com.wokite.net.backendstore.message.response.ResponseMessage;
import com.wokite.net.backendstore.models.HistoriqueLogin;
import com.wokite.net.backendstore.models.Role;
import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.RoleRepository;
import com.wokite.net.backendstore.repository.UtilisateurRepository;
import com.wokite.net.backendstore.security.jwt.JwtProvider;
import com.wokite.net.backendstore.security.service.UserPrinciple;
import com.wokite.net.backendstore.services.HistoriqueLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthController implements AuthApi {

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private HistoriqueLoginService historiqueLoginService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = userRepository.findById(userDetails.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        HistoriqueLogin historiqueLogin = new HistoriqueLogin();
        historiqueLogin.setUtilisateur(utilisateur);
        historiqueLogin.setAction("SE CONNECTER");
        historiqueLogin.setCreatedDate(new Date());
        historiqueLoginService.saveHistoriqueLogin(historiqueLogin);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {

        if (userRepository.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Utilisateur user = new Utilisateur(signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()));

        String[] roleArr = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();

        if (roleArr == null) {
            roles.add(roleRepository.findByName(RoleName.ROLE_VENDEUR).get());
        }

        for (String role : roleArr) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "gerant":
                    roles.add(roleRepository.findByName(RoleName.ROLE_GERANT).get());
                    break;

                case "vendeur":
                    roles.add(roleRepository.findByName(RoleName.ROLE_VENDEUR).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");
            }
        }

        user.setRoles(roles);

        user.setActive(true);

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.CREATED);

    }

}
