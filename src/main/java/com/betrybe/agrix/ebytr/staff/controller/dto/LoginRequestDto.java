package com.betrybe.agrix.ebytr.staff.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Login Request DTO.
 */
public record LoginRequestDto(String username, String password) {
  public UsernamePasswordAuthenticationToken toLoginData() {
    return new UsernamePasswordAuthenticationToken(username, password);
  }
}
