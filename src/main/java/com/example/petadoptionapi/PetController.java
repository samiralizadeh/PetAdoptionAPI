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
    public String addPet(@RequestBody Pet pet) {
        repository.save(pet);
        return "Pet added successfully!";
    }

    @DeleteMapping("/pets/{id}")
    public String deletePet(@PathVariable int id) {
        repository.deleteById(id);
        return "Pet deleted successfully!";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromBrowser(@PathVariable int id) {
        repository.deleteById(id);
        return "Pet with ID " + id + " deleted successfully!";
    }

    @PutMapping("/pets/{id}")
    public String updateAge(@PathVariable int id,
                            @RequestBody Pet pet) {

        repository.updateAge(id, pet.getAge());
        return "Pet age updated successfully!";
    }
}
