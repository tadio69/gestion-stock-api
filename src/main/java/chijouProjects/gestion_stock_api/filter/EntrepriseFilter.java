package chijouProjects.gestion_stock_api.filter;

import chijouProjects.gestion_stock_api.interceptor.Interceptor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class EntrepriseFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String entrepriseIdHeader = request.getHeader("X-Entreprise-Id");

        if (entrepriseIdHeader != null) {
            try {
                int entrepriseId = Integer.parseInt(entrepriseIdHeader);
                Interceptor.setCurrentEntrepriseId(entrepriseId);
            } catch (NumberFormatException ignored) {}
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            Interceptor.clear();
        }

    }
}

