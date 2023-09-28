package com.betrybe.agrix.exceptions;

/**
 * Exceção lançada ao não encontrar fertilizantes.
 */
public class FertilizerNotFoundException extends RuntimeException {

  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}
