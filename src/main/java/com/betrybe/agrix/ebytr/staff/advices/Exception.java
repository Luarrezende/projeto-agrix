package com.betrybe.agrix.ebytr.staff.advices;

import com.betrybe.agrix.ebytr.staff.exception.UsernameNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.WrongArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler.
 */
@ControllerAdvice
public class Exception {

  @ExceptionHandler(WrongArgumentException.class)
  public ResponseEntity<String> handleWrongArgumentException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<String> handleUsernameNotFoundException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
  }
}