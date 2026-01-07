package chijouProjects.gestion_stock_api.controller.auth;

import chijouProjects.gestion_stock_api.dto.auth.AuthenticationRequest;
import chijouProjects.gestion_stock_api.dto.auth.AuthenticationResponse;
import chijouProjects.gestion_stock_api.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Gestion de l'authentification et récupération du Token JWT")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "S'authentifier pour obtenir un Token",
            description = "Fournissez votre login et mot de passe. Le token retourné devra être utilisé dans le bouton 'Authorize' en haut de page."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentification réussie"),
            @ApiResponse(responseCode = "403", description = "Identifiants invalides")
    })
    @PostMapping("/login")
    public AuthenticationResponse login(
            @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
