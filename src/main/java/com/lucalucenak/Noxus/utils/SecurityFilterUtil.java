package com.lucalucenak.Noxus.utils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucalucenak.Noxus.services.ClientAccountService;
import com.lucalucenak.Noxus.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class SecurityFilterUtil extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private ClientAccountService clientAccountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var jwtToken = this.recoverJwtToken(request);
            if (jwtToken == null) {

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                Map<String, Object> errorDetails = new LinkedHashMap<>();
                errorDetails.put("status", "Access denied. No token provided.");

                Map<String, Object> errorResponse = new LinkedHashMap<>();
                errorResponse.put("errors", errorDetails);
                errorResponse.put("httpStatus", "FORBIDDEN");
                errorResponse.put("zonedDateTime", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

                String json = new ObjectMapper().writeValueAsString(errorResponse);

                response.getWriter().write(json);
                return;
            }

            var cpf = jwtTokenService.validateJwtToken(jwtToken);
            UserDetails userDetails = clientAccountService.findClientAccountByCpf(cpf);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e) {

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            Map<String, Object> errorDetails = new LinkedHashMap<>();
            errorDetails.put("status", "Token has expired");

            Map<String, Object> errorResponse = new LinkedHashMap<>();
            errorResponse.put("errors", errorDetails);
            errorResponse.put("httpStatus", "UNAUTHORIZED");
            errorResponse.put("zonedDateTime", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

            String json = new ObjectMapper().writeValueAsString(errorResponse);

            response.getWriter().write(json);

            return;
        }
    }


    private String recoverJwtToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
