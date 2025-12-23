package chijouProjects.gestion_stock_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               //1- Gestion des authorisations
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/public/**", "/login").permitAll() //Accès libre
                       .requestMatchers("/admin/**").hasRole("ADMIN") //Réservé aux admins
                       .anyRequest().authenticated() //Tout le reste nécessite un login
               )
               //2- Formulaire de login par défaut
               .formLogin(Customizer.withDefaults())
               //3- Optionnel: Déconnexion
               .logout(logout -> logout.logoutSuccessUrl("/"));
       return http.build(); //C'est ici qu'on construit la configuration
   }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("afint")
                .password("12345")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
