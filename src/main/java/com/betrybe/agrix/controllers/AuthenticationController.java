package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dtos.AuthenticationDto;
import com.betrybe.agrix.controllers.dtos.TokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller que recebe os endpoints de autenticação de usuário.
 */
@Tag(name = "Authentication", description = "endpoints de autenticação de usuário")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Construtor de authentication controller.
   */
  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      PersonService personService, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Recebe um username e senha e caso esteja tudo correto, envia um token de resposta.
   */
  @Operation(
      summary = "Realiza o login de pessoa usuária cadastrada",
      description = "caso usuário exista, retorna um token que será usado em outras operações"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          content = {
              @Content(
                  schema = @Schema(implementation = TokenDto.class),
                  mediaType = "application/json"
              )
          }),
      @ApiResponse(
          responseCode = "403",
          content = { @Content(schema = @Schema())}

      )
  })
  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
        authenticationDto.username(),
        authenticationDto.password()
    );

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String token = tokenService.generateToken(userDetails);

    return new TokenDto(token);
  }
}
