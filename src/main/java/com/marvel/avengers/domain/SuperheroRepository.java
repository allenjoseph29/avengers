package com.marvel.avengers.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SuperheroRepository extends JpaRepository<Superhero, Long> {
    List<Superhero> findByAssociationsContains(String association);
}
