package dev.naul.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.naul.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/candidate")) {
            SecurityContextHolder.getContext().setAuthentication(null);
            String header = request.getHeader("Authorization");

            if (header != null) {
                var tokenDecoded = this.jwtCandidateProvider.validateToken(header);

                if (tokenDecoded == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("candidate_id", tokenDecoded.getSubject());
                var roles = tokenDecoded.getClaim("roles").asList(Object.class);

                var grants = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                        .toList();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        tokenDecoded.getSubject(), null, grants);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

}
