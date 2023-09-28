package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada service de fertilizers.
 */
@Service
public class FertilizerService {

  FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer addFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Procura o fetilizante especificado pelo id.
   */
  public Fertilizer getFertilizerById(Long id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    return optionalFertilizer.get();
  }
}
