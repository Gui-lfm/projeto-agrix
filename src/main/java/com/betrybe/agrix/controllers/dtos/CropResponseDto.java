package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * Dto de resposta para crops.
 */
public record CropResponseDto(String name, double plantedArea, Long id, Long farmId,
                              LocalDate plantedDate,
                              LocalDate harvestDate) {

  /**
   * Transforma uma entidade em um dto de resposta.
   */
  public static CropResponseDto toResponse(Crop crop) {
    return new CropResponseDto(
        crop.getName(),
        crop.getPlantedArea(),
        crop.getId(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }

}
