package com.marvel.avengers.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    private List<String> powers;

    @ElementCollection
    private List<String> weapons;

    private String origin;

    @ElementCollection
    private List<String> associations;
}
