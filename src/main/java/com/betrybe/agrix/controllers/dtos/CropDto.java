package com.betrybe.agrix.controllers.dtos;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * DTO de crop.
 */
public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate) {

  /**
   * Conversor de DTO para entidade.
   */
  public Crop toCrop() {
    Crop crop = new Crop();
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setHarvestDate(harvestDate);
    return crop;
  }

  /**
   * Conversor de entidade para DTO.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );
  }
}
