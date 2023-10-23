package com.lucalucenak.Noxus.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${jwt.security.token}")
    private String secret;

    public String generateJwtToken(ClientAccountModel clientAccountModel) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String jwtToken = JWT.create()
                .withIssuer("auth-api")
                .withSubject(clientAccountModel.getCpf())
                .withExpiresAt(this.getExpirationDate())
                .sign(algorithm);

        return jwtToken;
    }

    public String validateJwtToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(jwtToken)
                .getSubject();
    }

    private Date getExpirationDate() {
        return Date.from(Instant.now().plus(Duration.ofDays(1)));
    }
}
