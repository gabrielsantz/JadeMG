package com.gabrielsantiago.jademg.security;

import com.gabrielsantiago.jademg.models.PortariaModel;
import com.gabrielsantiago.jademg.repositories.PortariaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PortariaRepository portariaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        logger.debug("Processing request for path: {}", path);

        if (path.startsWith("/auth/")) {
            logger.debug("Skipping authentication for auth path");
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        logger.debug("Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("No valid Authorization header found for path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7).trim();
            logger.debug("Processing token: {}", token);

            if (jwtUtil.isTokenValid(token)) {
                String username = jwtUtil.extractUsername(token);
                logger.debug("Token is valid, extracting username: {}", username);

                PortariaModel portaria = portariaRepository.findByUsername(username).orElse(null);
                if (portaria != null) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.debug("Successfully authenticated user: {}", username);
                } else {
                    logger.warn("User not found in database: {}", username);
                }
            } else {
                logger.warn("Invalid JWT token for path: {}", path);
            }
        } catch (Exception e) {
            logger.error("Error processing JWT token for path: {} - Error: {}", path, e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}