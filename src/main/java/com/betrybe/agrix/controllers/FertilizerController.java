package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.FarmDto;
import com.betrybe.agrix.controllers.dtos.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(
    name = "Fertilizer", description = "endpoints responsáveis pelo gerenciamento de fertilizantes."
)
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
  @Operation(
      summary = "Adiciona um fertilizante ao banco de dados. Necessário token de autorização "
          + "para efetuar a operação",
      description = "Retorna o objeto FertilizerDto criado, contendo os campos id, name, brand "
          + "e composition"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          content = {
              @Content(
                  schema = @Schema(implementation = FertilizerDto.class),
                  mediaType = "application/json"
              )
          }
      ),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
  @PostMapping
  public ResponseEntity<FertilizerDto> addFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = service.addFertilizer(fertilizerDto.toEntity());

    FertilizerDto savedFertilizer = FertilizerDto.fromEntity(fertilizer);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedFertilizer);
  }

  /**
   * Retorna uma lista com todos os fertilizantes presentes no banco de dados.
   */
  @Operation(
      summary = "Retorna uma lista de fertilizantes presentes no banco de dados. Necessário "
          + "token de autorização para efetuar a operação e usuário com role ADMIN",
      description =
          "Retorna uma lista de objetos FertilizerDto contendo os campos id, name, brand "
              + "e composition"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = FertilizerDto.class)),
                  mediaType = "application/json"
              )
          }
      ),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
  })
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
  @Operation(
      summary = "Retorna o fertilizante especificado pelo id (caso exista). Necessário "
          + "token de autorização para efetuar a operação.",
      description =
          "Retorna um objeto FertilizerDto contendo os campos id, name, brand "
              + "e composition"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {
              @Content(
                  schema = @Schema(implementation = FertilizerDto.class),
                  mediaType = "application/json"
              )
          }
      ),
      @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema())}),
      @ApiResponse(
          responseCode = "404",
          description = "Fertilizante não encontrado!",
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
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = service.getFertilizerById(id);
    FertilizerDto requestedFertilizer = FertilizerDto.fromEntity(fertilizer);

    return ResponseEntity.ok(requestedFertilizer);
  }
}
