package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de crops.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  CropService service;

  @Autowired
  public CropController(CropService service) {
    this.service = service;
  }

  /**
   * Retorna uma lista com todas as plantações presentes no banco de dados.
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  public List<CropResponseDto> getCrops() {
    return service.getAllCrops().stream()
        .map(CropResponseDto::toResponse)
        .toList();
  }

  /**
   * Caso exista, retorna a plantação indicada pelo id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropResponseDto> getCropById(@PathVariable Long id) {
    Crop crop = service.getCropById(id);
    CropResponseDto cropResponseDto = CropResponseDto.toResponse(crop);
    return ResponseEntity.ok(cropResponseDto);
  }

  /**
   * Recebe um id de plantação e um de fazenda para realizar a associação.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateFertilizerToCrop(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    service.associateFertilizertoCrop(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Recebe um id de plantação e retorna uma lista de fertilizantes.
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getCropFertilizers(@PathVariable Long cropId) {
    return service.getCropFertilizers(cropId)
        .stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Retorna uma lista de plantações que estão com o HarvestDate entre as datas especificadas.
   */
  @GetMapping("/search")
  public List<CropResponseDto> getCropsByDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    return service.getCropsByHarvestDate(start, end)
        .stream()
        .map(CropResponseDto::toResponse)
        .toList();
  }
}
