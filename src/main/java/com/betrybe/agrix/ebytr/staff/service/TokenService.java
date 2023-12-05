package com.betrybe.agrix.ebytr.staff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Token Service.
 */
@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  String secret;

  /**
   * Validation secret.
   */
  public String validate(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm).withIssuer("agrix")
        .build().verify(token).getSubject();
  }

  /**
   * Generate token.
   */
  public String generate(UserDetails userDetails) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    // Você pode acessar informações do usuário usando o UserDetails
    return JWT.create()
            .withIssuer("agrix")
            .withSubject(userDetails.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  /**
   * Generate expiration date.
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }
}
