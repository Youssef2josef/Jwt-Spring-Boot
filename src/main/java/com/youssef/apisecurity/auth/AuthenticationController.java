package com.youssef.apisecurity.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthenticationController {

  private final AuthenticationService service;
  @Operation(
          description = "Register endpoint VIA POST",
          summary = "This is a summary for Register endpoint",
          responses = {
                  @ApiResponse(
                          description = "Created",
                          responseCode = "201"
                  ),
                  @ApiResponse(
                          description = "Bad Request",
                          responseCode = "400"
                  ),
                  @ApiResponse(
                          description = "Unauthorized / Invalid Token",
                          responseCode = "403"
                  )

          }

  )

  @PostMapping("/register")
  public ResponseEntity<?> register(
          @Valid @RequestBody RegisterRequest request
  ) {
    return service.register(request);
  }
    @Operation(
            description = "Login endpoint VIA POST",
            summary = "This is a summary for Login endpoint",
            responses = {
                    @ApiResponse(
                            description = "Accepted",
                            responseCode = "202"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    ),
            }

    )
  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(
         @Valid @RequestBody AuthenticationRequest request
  ) {
    return service.authenticate(request);
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
