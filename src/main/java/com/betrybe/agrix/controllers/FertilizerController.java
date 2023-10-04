package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada controller de fertilizer.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  FertilizerService service;

  /**
   * Construtor.
   */
  @Autowired
  public FertilizerController(FertilizerService service) {
    this.service = service;
  }

  /**
   * Adiciona um fertilizante ao banco de dados.
   */
  @PostMapping
  public ResponseEntity<FertilizerDto> addFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = service.addFertilizer(fertilizerDto.toEntity());

    FertilizerDto savedFertilizer = FertilizerDto.fromEntity(fertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedFertilizer);
  }

  /**
   * Retorna uma lista com todos os fertilizantes presentes no banco de dados.
   */
  @GetMapping
  @Secured("ROLE_ADMIN")
  public List<FertilizerDto> getAllFertilizers() {
    return service.getAllFertilizers()
        .stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Retorna um fertilizante especificado pelo id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = service.getFertilizerById(id);
    FertilizerDto requestedFertilizer = FertilizerDto.fromEntity(fertilizer);

    return ResponseEntity.ok(requestedFertilizer);
  }
}
