package com.marvel.avengers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperheroService {
    private final SuperheroRepository superheroRepository;

    public Long createSuperhero(SuperheroDTO superheroDTO) {
        Superhero superhero = SuperheroMapper.convertToEntity(superheroDTO);
        return superheroRepository.save(superhero).getId();
    }
}
