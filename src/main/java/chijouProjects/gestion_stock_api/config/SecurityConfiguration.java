package chijouProjects.gestion_stock_api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // 1. On définit l'encodeur comme un Bean pour que Spring puisse l'utiliser partout
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .csrf(csrf -> csrf.disable())
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
        // 2. On utilise l'encodeur manuellement ici pour hacher le mot de passe
        String motDePasseHache = passwordEncoder().encode("tadio6919");
        UserDetails user = User.builder()
                .username("chijou")
                .password(motDePasseHache)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
