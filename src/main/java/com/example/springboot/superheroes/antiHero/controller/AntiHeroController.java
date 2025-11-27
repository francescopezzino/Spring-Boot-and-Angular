package com.example.springboot.superheroes.antiHero.controller;

import com.example.springboot.superheroes.antiHero.dto  .AntiHeroDto;
import com.example.springboot.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.springboot.superheroes.antiHero.service.AntiHeroService;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * We can combine the application of CORS at both the controller and method levels in our application
 */
@CrossOrigin(allowedHeaders = "Content-type")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/anti-heroes")
@PreAuthorize("isAuthenticated()")
public class AntiHeroController {


    private final AntiHeroService service;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.find2AntiHeroById(id));
    }

    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AntiHeroDto antiHeroDto
    ) {

        if (!id.equals(antiHeroDto.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );
        var antiHeroEntity = convertToEntity(antiHeroDto);
        service.updateAntiHero(id, antiHeroEntity);
    }

    @PostMapping
    public AntiHeroDto postAntiHero(@Valid @RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);
        var antiHero = service.addAntiHero(entity);

        return convertToDto(antiHero);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    /**
     * method that will return all the entities in the database
     * service.findAllAntiHeroes() method to get the entities in the database.
     * Since this returns to an Iterable instance, we will convert it into a stream and
     * transform it into a list using Collectors.toList()
     */
//    @GetMapping
//    public List<AntiHeroDto> getAntiHeroes() {
//        var antiHeroList = StreamSupport
//                .stream(service.findAllAntiHeroes().spliterator(),
//                        false)
//                .collect(Collectors.toList());
//        return antiHeroList
//                .stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    /*
    We can enable CORS on a single endpoint;
    this means that we can specify different permitted origins for other endpoints.
    We can also specify the configuration of the CORS policy by adding the values of the origin,
    methods, allowedHeaders, exposedHeaders, allowedCredentials, and maxAge
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<AntiHeroDto> getAntiHeroes(Pageable pageable) {
        int toSkip = pageable.getPageSize() *
                pageable.getPageNumber();
        var antiHeroList = StreamSupport
                .stream(service.findAllAntiHeroes().spliterator(), false)
                .skip(toSkip).limit(pageable.getPageSize())
                .toList(); // replaced from --> :  .collect(Collectors.toList());
        return antiHeroList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }
    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }
}