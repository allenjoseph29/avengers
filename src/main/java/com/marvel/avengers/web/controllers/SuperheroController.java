package com.marvel.avengers.web.controllers;

import com.marvel.avengers.domain.PagedResult;
import com.marvel.avengers.domain.SuperheroDTO;
import com.marvel.avengers.domain.SuperheroService;
import com.marvel.avengers.web.exception.SuperheroNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/superheroes")
public class SuperheroController {
    private final SuperheroService superheroService;

    @Operation(
            summary = "Create a new superhero.",
            description = "Name and alias are required fields.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Superhero created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
                    })
            }
    )
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

    @Operation(
            summary = "Get Superhero by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Superhero found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Superhero not found.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
                    })
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SuperheroDTO> getSuperhero(@PathVariable Long id) {
        return superheroService.getSuperhero(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> SuperheroNotFound.forId(id));
    }

    @Operation(
            summary = "Get all superheroes",
            description = "Provide a page number to get a list of superheroes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Superheroes found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PagedResult.class))
                    })
            }
    )
    @GetMapping
    public PagedResult<SuperheroDTO> getAllSuperheroes(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return superheroService.getAllSuperheroes(pageNo);
    }

    @GetMapping("/association")
    public List<SuperheroDTO> getAllSuperheroesByAssociation(@RequestParam(name = "association") String association) {
        return superheroService.getAllSuperheroesByAssociation(association);
    }
}
