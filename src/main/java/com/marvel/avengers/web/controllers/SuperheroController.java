package com.marvel.avengers.web.controllers;

import com.marvel.avengers.domain.SuperheroDTO;
import com.marvel.avengers.domain.SuperheroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/superheroes")
public class SuperheroController {
    private final SuperheroService superheroService;

    @PostMapping
    public ResponseEntity<SuperheroDTO> createSuperhero(
            @Valid @RequestBody SuperheroDTO superheroDTO) {
        Long id = superheroService.createSuperhero(superheroDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
