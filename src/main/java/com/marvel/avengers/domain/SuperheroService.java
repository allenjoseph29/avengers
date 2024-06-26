package com.marvel.avengers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public PagedResult<SuperheroDTO> getAllSuperheroes(int pageNo) {
        Sort sort = Sort.by("alias").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, 3, sort);
        Page<SuperheroDTO> superheroPage = superheroRepository.findAll(pageable).map(SuperheroMapper::convertToDTO);

        return new PagedResult<>(
                superheroPage.getContent(),
                superheroPage.getTotalElements(),
                superheroPage.getNumber() + 1,
                superheroPage.getTotalPages(),
                superheroPage.isFirst(),
                superheroPage.isLast(),
                superheroPage.hasNext(),
                superheroPage.hasPrevious());
    }

    public List<SuperheroDTO> getAllSuperheroesByAssociation(String association) {
        return superheroRepository.findByAssociationsContains(association).stream()
                .map(SuperheroMapper::convertToDTO)
                .toList();
    }
}
