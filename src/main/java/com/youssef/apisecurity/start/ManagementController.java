package com.youssef.apisecurity.start;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {


    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),
            }

    )
    @GetMapping
    public String get() {
        return "GET:: management controller";
    }
    @Operation(
            description = "POST endpoint for manager",
            summary = "This is a summary for management post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),
            }

    )
    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    @Operation(
            description = "PUT endpoint for manager",
            summary = "This is a summary for management update endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),
            }

    )
    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }
    @Operation(
            description = "DELETE endpoint for manager",
            summary = "This is a summary for management suppression endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    ),
            }

    )
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
