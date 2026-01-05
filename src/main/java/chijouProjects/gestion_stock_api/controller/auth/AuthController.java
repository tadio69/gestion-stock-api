package chijouProjects.gestion_stock_api.controller.auth;

import chijouProjects.gestion_stock_api.dto.auth.AuthenticationRequest;
import chijouProjects.gestion_stock_api.dto.auth.AuthenticationResponse;
import chijouProjects.gestion_stock_api.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(
            @RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
