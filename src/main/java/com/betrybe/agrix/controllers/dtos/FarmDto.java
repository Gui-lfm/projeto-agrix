package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Farm;

/**
 * Dto de fazenda.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Método de conversão de DTO para Entidade.
   */
  public Farm toFarm() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }

  /**
   * Método de conversão de entidade para Dto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }
}
