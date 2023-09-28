package com.betrybe.agrix.exceptions;

/**
 * Exceção lançada ao não encontrar a fazenda requisitada.
 */
public class FarmNotFoundException extends RuntimeException {

  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
