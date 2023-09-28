package com.betrybe.agrix.advice;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Advice for exceptions.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({FarmNotFoundException.class, CropNotFoundException.class,
      FertilizerNotFoundException.class})
  public ResponseEntity<String> handleNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}
