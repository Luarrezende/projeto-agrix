package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.LoginRequestDto;
import com.betrybe.agrix.ebytr.staff.exception.WrongArgumentException;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth Controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  
  /**
   * Constructor.
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager,
                        TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
    try {
      UsernamePasswordAuthenticationToken usernamePassword =
          new UsernamePasswordAuthenticationToken(loginRequestDto
              .username(), loginRequestDto.password());
      Authentication auth = authenticationManager.authenticate(usernamePassword);
      User person = (User) auth.getPrincipal();
      TokenResponse response = new TokenResponse(tokenService.generate(person));
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      throw new WrongArgumentException();
    }
  }

  private record TokenResponse(String token) {}
}
