package com.one.challengeforumhub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.one.challengeforumhub.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String gerarToken(final Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getLogin())
                .withExpiresAt(getDataExpiracao())
                .sign(getAlgorithm());
    }

    public String verificarToken(final String token) {
        return JWT.require(getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }

    private Instant getDataExpiracao() {
        return LocalDateTime.now()
                .plusHours(1)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
