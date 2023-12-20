package com.youssef.apisecurity.start;

import com.youssef.apisecurity.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/agents")
@Tag(name = "Operations", description = "Endpoints for managing agents, including creation, retrieval, update, and deletion.")
public class OperationClassifiedController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OperationService operationService;

    @Operation(
            description = "POST endpoint for Operation Classified",
            summary = "This is a summary for Classified Post endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
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
                    ),
            }

    )
    @PostMapping
    public ResponseEntity<?> ajouterAgent(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email Déja Utilisé!"));
        }
        if (user.getRole() == null || user.getEmail() == null || user.getPassword() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Veuillez saisir toutes" +
                    "les champs nécaissires!!!"));
        }

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User Has Been Created."));
    }
    @Operation(
            description = "Get endpoint for Operation Classified",
            summary = "This is a summary for Classified get endpoint",
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
    public List<User> recupererAllAgents(){
        return operationService.getAllUsers();
    }

    @Operation(
            description = "Get By ID endpoint for Operation Classified",
            summary = "This is a summary for Classified get endpoint",
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
    @GetMapping("/{id}")
    public User recupererOneAgent(@PathVariable Integer id) {
        return operationService.getUserById(id);
    }
    @Operation(
            description = "Put endpoint for Operation Classified",
            summary = "This is a summary for Classified update endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
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

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        User existingUser = operationService.getUserById(user.getId());

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error: Utilisateur non trouvé !"));
        }

        if (user.getFirstname() != null && !user.getFirstname().isEmpty()) {
            existingUser.setFirstname(user.getFirstname());
        }

        if (user.getLastname() != null && !user.getLastname().isEmpty()) {
            existingUser.setLastname(user.getLastname());
        }

        // Si il y a un utilisateur ayant le meme Email que l'Email rendu dans Objet User
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (userRepository.existsByEmail(user.getEmail())) {
                Optional<User> userFound = userRepository.findByEmail(user.getEmail());
                if(existingUser.getId() != userFound.get().getId()) {
                    return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Email Dupliqué !"));
                }
            }
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }


        // Save the updated user to the database
        User updatedUser = operationService.updateOneUser(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @Operation(
            description = "Delete endpoint for Operation Classified",
            summary = "This is a summary for Classified Suppression endpoint",
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
    @DeleteMapping("/{id}")
    public void supprimerAgent(@PathVariable Integer id) {
        operationService.deleteUser(id);
    }
}
