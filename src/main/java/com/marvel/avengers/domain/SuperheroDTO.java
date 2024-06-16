package com.marvel.avengers.domain;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SuperheroDTO(@NotEmpty(message = "Alias is required for a Superhero")String alias,
                           @NotEmpty(message = "Name is required for a Superhero.")String name,
                           List<String> powers, List<String> weapons, String origin,
                           List<String> associations) {
}
