package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.models.entities.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * classe responsável por gerenciar os métodos relacionados ao token.
 */
@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Gera um token a partir das informações do usuário.
   */
  public String generateToken(UserDetails userDetails) {

    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("Agrix")
        .withSubject(userDetails.getUsername())
        .withExpiresAt(expirationDate())
        .sign(algorithm);
  }

  /**
   * Define uma data de validade do token criado.
   */
  private Instant expirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Verifica se o token informado é valido.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.require(algorithm)
        .withIssuer("Agrix")
        .build()
        .verify(token)
        .getSubject();
  }
}
