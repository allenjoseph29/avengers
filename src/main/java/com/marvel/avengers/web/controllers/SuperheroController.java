package com.marvel.avengers.web.controllers;

import com.marvel.avengers.domain.PagedResult;
import com.marvel.avengers.domain.SuperheroDTO;
import com.marvel.avengers.domain.SuperheroService;
import com.marvel.avengers.web.exception.SuperheroNotFound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<SuperheroDTO> getSuperhero(@PathVariable Long id) {
        return superheroService.getSuperhero(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> SuperheroNotFound.forId(id));
    }

    @GetMapping
    public PagedResult<SuperheroDTO> getAllSuperheroes(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return superheroService.getAllSuperheroes(pageNo);
    }
}
