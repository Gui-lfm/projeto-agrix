package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.controllers.dtos.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
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
 * Camada controller de Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  FarmService service;

  @Autowired
  public FarmController(FarmService service) {
    this.service = service;
  }

  /**
   * Recebe os dados de uma fazenda e os adiciona ao banco de dados.
   */
  @PostMapping
  public ResponseEntity<FarmDto> addFarm(@RequestBody FarmDto newFarm) {
    Farm farm = service.addFarm(newFarm.toFarm());
    FarmDto createdFarm = FarmDto.fromEntity(farm);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
  }

  /**
   * Retorna uma lista com as fazendas armazendas no banco de dados.
   */
  @GetMapping
  @Secured({"ADMIN", "MANAGER", "USER"})
  public List<FarmDto> getAllFarms() {
    return service.getAllFarms()
        .stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Retorna a fazenda especificada pelo id (caso exista).
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Optional<Farm> optionalFarm = service.getFarmById(id);
    FarmDto farmDto = FarmDto.fromEntity(optionalFarm.get());

    return ResponseEntity.ok(farmDto);
  }

  // crops

  /**
   * Adiciona uma plantação à fazenda indicada pelo id.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropResponseDto> createCrop(@PathVariable Long farmId,
      @RequestBody CropDto newCrop) {
    Crop crop = service.createFarmCrop(farmId, newCrop.toCrop());

    CropResponseDto cropResponseDto = CropResponseDto.toResponse(crop);

    return ResponseEntity.status(HttpStatus.CREATED).body(cropResponseDto);
  }


  /**
   * Retorna todas as plantações da fazenda indicada pelo id.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropResponseDto> getCrops(@PathVariable Long farmId) {
    return service.getCropsFromFarm(farmId).stream()
        .map(CropResponseDto::toResponse)
        .toList();
  }
}
