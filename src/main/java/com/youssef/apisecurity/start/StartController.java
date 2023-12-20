package com.youssef.apisecurity.start;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/Execution")
@Hidden
public class StartController {

  @GetMapping
  public ResponseEntity<String> start() {
    return ResponseEntity.ok("Starter Secured endpoint");
  }

}
