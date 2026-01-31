package com.example.petadoptionapi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepository {

    private final JdbcTemplate jdbc;

    public PetRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Pet> findAll() {
        return jdbc.query(
                "SELECT * FROM pet",
                (rs, rowNum) -> new Pet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("age")
                )
        );
    }

    public void save(Pet pet) {
        jdbc.update(
                "INSERT INTO pet(name, type, age) VALUES (?, ?, ?)",
                pet.getName(),
                pet.getType(),
                pet.getAge()
        );
    }

    public void deleteById(int id) {
        jdbc.update(
                "DELETE FROM pet WHERE id = ?",
                id
        );
    }

    public void updateAge(int id, int age) {
        jdbc.update(
                "UPDATE pet SET age = ? WHERE id = ?",
                age,
                id
        );
    }
}
