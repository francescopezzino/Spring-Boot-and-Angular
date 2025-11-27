package com.example.springboot.superheroes.antiHero.service;

import com.example.springboot.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.springboot.superheroes.antiHero.repository.AntiHeroRepository;
import com.example.springboot.superheroes.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AntiHeroService {
    private final AntiHeroRepository repo;

    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return repo.findAll();
    }
    public Optional<AntiHeroEntity> findAntiHeroById(UUID id) {
        return repo.findById(id);
    }

    public void removeAntiHeroById(UUID id) {
        repo.deleteById(id);
    }
    public AntiHeroEntity addAntiHero( AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }
    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    public AntiHeroEntity find2AntiHeroById(UUID id) {
        return findOrThrow(id);
    }
    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo.findById(id).orElseThrow( () -> new NotFoundException("Anti-hero by id " + id + " was not found") );
    }
}