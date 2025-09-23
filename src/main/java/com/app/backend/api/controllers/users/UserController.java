package com.app.backend.api.controllers.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(List.of());
    }
}
