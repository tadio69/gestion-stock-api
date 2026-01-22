package chijouProjects.gestion_stock_api.service.auth;

import chijouProjects.gestion_stock_api.dto.auth.AuthenticationRequest;
import chijouProjects.gestion_stock_api.dto.auth.AuthenticationResponse;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.repository.UtilisateurRepository;
import chijouProjects.gestion_stock_api.service.security.JwtService;
import chijouProjects.gestion_stock_api.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;
    private final ApplicationUserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getMotdepasse()
                    )
            );
        } catch (BadCredentialsException ex) {
            throw new InvalidEntityException(
                    Constants.AUTH_INCORRECT, // message principal
                    ErrorCodes.BAD_CREDENTIALS,// code d'erreur 15000
                    new ArrayList<>(List.of(
                            "L'email ou le mot de passe fourni n'est pas correct" // détails
                    ))
            );
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        String jwt = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().accessToken(jwt).build();
    }
}
