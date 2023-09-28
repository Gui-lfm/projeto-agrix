package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço de Farm.
 */
@Service
public class FarmService {

  FarmRepository farmRepository;
  CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm addFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Retorna a fazenda especificada pelo id caso exista.
   */
  public Optional<Farm> getFarmById(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = optionalFarm.get();
    return Optional.of(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Atualiza a fazenda especificada pelo id caso exista.
   */
  public Optional<Farm> updateFarm(Long id, Farm farm) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      return Optional.empty();
    }

    Farm farmFromDb = optionalFarm.get();
    farmFromDb.setName(farm.getName());
    farmFromDb.setSize(farm.getSize());

    Farm updatedFarm = farmRepository.save(farmFromDb);

    return Optional.of(updatedFarm);
  }

  /**
   * Deleta uma fazenda do banco de dados.
   */
  public Optional<Farm> deleteFarm(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isPresent()) {
      farmRepository.deleteById(id);
    }

    return optionalFarm;
  }

  // Métodos das plantações de fazenda.

  /**
   * Adiciona uma plantação à fazenda indicada pelo id.
   */
  public Crop createFarmCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = optionalFarm.get();

    crop.setFarm(farm);
    return cropRepository.save(crop);

  }

  /**
   * Retorna as plantações da fazenda.
   */
  public List<Crop> getCropsFromFarm(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    Farm farm = optionalFarm.get();

    return farm.getCrops();
  }

}
