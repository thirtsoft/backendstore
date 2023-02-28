package com.wokite.net.backendstore.controllers.api;

import com.wokite.net.backendstore.message.request.LoginForm;
import com.wokite.net.backendstore.message.request.SignUpForm;
import com.wokite.net.backendstore.models.Utilisateur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.wokite.net.backendstore.utils.Constants.APP_ROOT;

public interface AccountApi {

    @PostMapping(value = APP_ROOT + "/auth/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Authentifier ",
            notes = "Cette méthode permet d'authentifier un utilisateur", response = LoginForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur est authentifié avec succès"),
            @ApiResponse(code = 400, message = "Error d'authentification, veuillez vérifer vos identifiants")

    })
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = APP_ROOT + "/auth/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Créer un compte ",
            notes = "Cette méthode permet à un utilisateur de créer son compte", response = SignUpForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utlisateur a été crée"),
            @ApiResponse(code = 400, message = "Aucun utilisateur n'a été crée")

    })
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest);


}
