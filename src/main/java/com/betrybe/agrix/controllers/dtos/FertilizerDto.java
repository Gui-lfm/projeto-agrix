package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * DTO de fertilizer.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Método para transformar o dto em entidade.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(name);
    fertilizer.setBrand(brand);
    fertilizer.setComposition(composition);

    return fertilizer;
  }

  /**
   * Método para transformar a entidade em dto.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
