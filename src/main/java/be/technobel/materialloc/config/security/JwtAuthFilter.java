package be.technobel.materialloc.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

        private final JwtProvider jwtProvider;

        public JwtAuthFilter(JwtProvider jwtProvider) {
            this.jwtProvider = jwtProvider;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            // 1. extraire le token
            String token = jwtProvider.extractToken(request);
            // 2. valider le token
            if( token != null && jwtProvider.validateToken(token) ){
                // 3. recupérer la personne liée au token
                // 4. créer une Authentication pour le user
                Authentication auth = jwtProvider.createAuthentication(token);
                // 5. Placer l'authentication dans le SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        }
    }