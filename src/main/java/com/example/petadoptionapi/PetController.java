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

    @GetMapping("/pets")
    public Iterable<Pet> getPets() {
        return repo.findAll();
    }

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping("/pets")
    public Pet addPet(@RequestBody Pet pet) {
        return repo.save(pet);
    }

    @DeleteMapping("/pets/{id}")
    public String deletePet(@PathVariable int id) {
        repo.deleteById(id);
        return "Pet deleted with id = " + id;
    }
}
