package com.example.petadoptionapi;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PetController {

    private final PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pets")
    public List<Pet> getPets() {
        return repository.findAll();
    }

    @PostMapping("/pets")
    public Pet addPet(@RequestBody Pet pet) {

        if (pet.getAge() < 0) {
            throw new InvalidAgeException("Age cannot be negative");
        }

        if (!pet.getName().matches("[A-Za-z]+")) {
            throw new RuntimeException("Name must contain only letters");
        }

        if (!pet.getType().equals("Dog") && !pet.getType().equals("Cat")) {
            throw new RuntimeException("Type must be Dog or Cat");
        }

        repository.savePet(pet);
        return pet;
    }

    @PutMapping("/pets/{id}")
    public String updatePet(@PathVariable int id,
                            @RequestBody Pet pet) {

        repository.updatePet(
                id,
                pet.getName(),
                pet.getType(),
                pet.getAge()
        );

        return "Pet updated successfully!";
    }

    @DeleteMapping("/pets/{id}")
    public String deletePet(@PathVariable int id) {
        repository.deleteById(id);
        return "Pet deleted successfully!";
    }
}
