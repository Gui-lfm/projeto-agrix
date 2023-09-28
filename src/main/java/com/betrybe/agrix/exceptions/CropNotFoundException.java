package com.betrybe.agrix.exceptions;

/**
 * Exceção lançada ao não encontrar plantações.
 */
public class CropNotFoundException extends RuntimeException {

  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
