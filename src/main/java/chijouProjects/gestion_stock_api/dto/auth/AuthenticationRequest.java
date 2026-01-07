package chijouProjects.gestion_stock_api.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String login;//ou email

    private String motdepasse;
}
