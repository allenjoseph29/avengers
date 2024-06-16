package com.marvel.avengers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuperheroService {
    private final SuperheroRepository superheroRepository;

    public Long createSuperhero(SuperheroDTO superheroDTO) {
        Superhero superhero = SuperheroMapper.convertToEntity(superheroDTO);
        return superheroRepository.save(superhero).getId();
    }

    public Optional<SuperheroDTO> getSuperhero(Long id) {
        return superheroRepository.findById(id).map(SuperheroMapper::convertToDTO);
    }
}
