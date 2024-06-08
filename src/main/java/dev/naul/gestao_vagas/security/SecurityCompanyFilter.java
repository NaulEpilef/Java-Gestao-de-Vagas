package dev.naul.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.naul.gestao_vagas.providers.JWTCompanyProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCompanyProvider jwtCompanyProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/company")) {
            SecurityContextHolder.getContext().setAuthentication(null);
            var header = request.getHeader("Authorization");

            if (header != null) {
                var tokenDecoded = this.jwtCompanyProvider.validateToken(header);

                if (tokenDecoded == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                var roles = tokenDecoded.getClaim("roles").asList(Object.class);

                var grants = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                        .toList();

                request.setAttribute("company_id", tokenDecoded.getSubject());
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        tokenDecoded.getSubject(), null,
                        grants);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

}
