package chijouProjects.gestion_stock_api.service.auth;

import chijouProjects.gestion_stock_api.dto.auth.AuthenticationRequest;
import chijouProjects.gestion_stock_api.dto.auth.AuthenticationResponse;
import chijouProjects.gestion_stock_api.model.Utilisateur;
import chijouProjects.gestion_stock_api.repository.UtilisateurRepository;
import chijouProjects.gestion_stock_api.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;
    private final ApplicationUserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Tentative de login pour : " + request.getLogin());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getMotdepasse()
                    )
            );
            System.out.println("Authentification réussie !");
        } catch (Exception e) {
            System.out.println("ÉCHEC AUTH : " + e.getMessage());
            throw e;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        String jwt = jwtService.generateToken(userDetails);
        return AuthenticationResponse.builder().accessToken(jwt).build();
    }
}
