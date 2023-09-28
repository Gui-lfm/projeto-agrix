package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de crops.
 */
@Service
public class CropService {

  CropRepository cropRepository;
  FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Procura a plantação indicada pelo id informado.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return optionalCrop.get();
  }

  // Fertilizers

  /**
   * Associa um fertilizante a uma plantação pelos respectivos ids.
   */
  public void associateFertilizertoCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    Crop crop = optionalCrop.get();
    crop.getFertilizers().add(optionalFertilizer.get());
    cropRepository.save(crop);

  }

  /**
   * Retorna uma lista de fertilizantes da plantação.
   */
  public List<Fertilizer> getCropFertilizers(Long cropId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = optionalCrop.get();

    return crop.getFertilizers();
  }

  public List<Crop> getCropsByHarvestDate(LocalDate start, LocalDate end) {

    return cropRepository.findByHarvestDateBetween(start, end);
  }
}
