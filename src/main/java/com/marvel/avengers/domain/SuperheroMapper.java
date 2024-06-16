package com.marvel.avengers.domain;


class SuperheroMapper {

    static Superhero convertToEntity(SuperheroDTO superheroDTO) {
        Superhero superhero = new Superhero();
        superhero.setAlias(superheroDTO.alias());
        superhero.setName(superheroDTO.name());
        superhero.setPowers(superheroDTO.powers());
        superhero.setWeapons(superheroDTO.weapons());
        superhero.setOrigin(superheroDTO.origin());
        superhero.setAssociations(superheroDTO.associations());
        return superhero;
    }

    static SuperheroDTO convertToDTO(Superhero superhero) {
        return new SuperheroDTO(superhero.getAlias(), superhero.getName(), superhero.getPowers(),
                superhero.getWeapons(), superhero.getOrigin(), superhero.getAssociations());
    }
}
