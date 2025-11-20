package chijouProjects.gestion_stock_api.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionStockOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion de Stock REST API")
                        .description("Documentation de l'API de gestion de stock")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Roger Chijou")
                                .email("duro.chijou@gmail.com")
                        )
                        .license(new License().name("Apache 2.0"))
                );
    }
}
