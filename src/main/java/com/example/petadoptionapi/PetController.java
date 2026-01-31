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
