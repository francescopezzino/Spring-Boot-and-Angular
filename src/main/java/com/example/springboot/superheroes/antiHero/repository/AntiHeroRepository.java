package com.example.springboot.superheroes.antiHero.repository;

import com.example.springboot.superheroes.antiHero.entity.AntiHeroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * methods provided by the JPA repository:
 * findAll(): Gets all the existing data in a specific entity
 * findById(Id): Finds a particular database by PK
 * save(): Inserts new data in the table
 * save(data): Updates existing data in the table
 * deleteById(id): Deletes specific data in the table by PK
 */
public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {
}
