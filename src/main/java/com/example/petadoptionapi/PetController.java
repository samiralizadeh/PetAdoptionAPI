package com.example.petadoptionapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {

    private final PetRepository repo;

    public PetController(PetRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, REST API works!";
    }

    @GetMapping("/pets")
    public List<Pet> getPets() {
        return repo.findAll();
    }
}
