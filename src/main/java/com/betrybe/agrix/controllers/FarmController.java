package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.CropDto;
import com.betrybe.agrix.controllers.dtos.CropResponseDto;
import com.betrybe.agrix.controllers.dtos.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Farm", description = "Gerenciamento das fazendas presentes no banco de dados")
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
  @Operation(
      summary = "Adiciona uma fazenda ao banco de dados. Necessário token de autorização "
          + "para efetuar a operação",
      description = "Retorna um objeto FarmDto contendo os campos id, name e size da fazenda"
  )
  @ApiResponse(
      responseCode = "201",
      content = {
          @Content(
              schema = @Schema(implementation = FarmDto.class),
              mediaType = "application/json"
          )
      }
  )
  @PostMapping
  public ResponseEntity<FarmDto> addFarm(@RequestBody FarmDto newFarm) {
    Farm farm = service.addFarm(newFarm.toFarm());
    FarmDto createdFarm = FarmDto.fromEntity(farm);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdFarm);
  }

  /**
   * Retorna uma lista com as fazendas armazendas no banco de dados.
   */
  @Operation(
      summary = "Lista as fazendas presentes no banco de dados. Necessário token de autorização "
          + "para efetuar a operação e usuário com role ADMIN ou MANAGER",
      description = "Retorna uma lista de FarmDto, contendo os campos id, name e size"
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = FarmDto.class)),
              mediaType = "application/json"
          )
      }),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  public List<FarmDto> getAllFarms() {
    return service.getAllFarms()
        .stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Retorna a fazenda especificada pelo id (caso exista).
   */
  @Operation(
      summary = "Retorna a fazenda especificada pelo id (caso exista). Necessário token de "
          + "autorização para efetuar a operação.",
      description = "Retorna um objeto FarmDto, contendo os campos id, name e size."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(
              schema = @Schema(implementation = FarmDto.class),
              mediaType = "application/json"
          )
      }),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404",
          description = "Fazenda não encontrada!",
          content = {
              @Content(
                  schema = @Schema(implementation = String.class),
                  mediaType = "text/plain"
              )
          }
      ),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
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
  @Operation(
      summary = "Adiciona uma plantação à fazenda indicada pelo id. Necessário token de "
          + "autorização para efetuar a operação.",
      description = "Retorna um objeto CropResponseDto, contendo os campos name, plantedArea, id, "
          + "farmId, plantedDate e harvestDate."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(
              schema = @Schema(implementation = CropResponseDto.class),
              mediaType = "application/json"
          )
      }),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404",
          description = "Fazenda não encontrada!",
          content = {
              @Content(
                  schema = @Schema(implementation = String.class),
                  mediaType = "text/plain"
              )
          }
      ),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
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
  @Operation(
      summary = "Lista as plantações da fazenda indicada pelo id. Necessário token de autorização",
      description = "Retorna uma lista de CropResponseDto, contendo os campos name, "
          + "plantedArea, id, farmId, plantedDate e harvestDate."
  )
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(
              array = @ArraySchema(schema = @Schema(implementation = CropResponseDto.class)),
              mediaType = "application/json"
          )
      }),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "404",
          description = "Fazenda não encontrada!",
          content = {
              @Content(
                  schema = @Schema(implementation = String.class),
                  mediaType = "text/plain"
              )
          }
      ),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
  @GetMapping("/{farmId}/crops")
  public List<CropResponseDto> getCrops(@PathVariable Long farmId) {
    return service.getCropsFromFarm(farmId).stream()
        .map(CropResponseDto::toResponse)
        .toList();
  }
}
