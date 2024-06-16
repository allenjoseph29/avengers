package com.marvel.avengers.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface SuperheroRepository extends JpaRepository<Superhero, Long> {
}
