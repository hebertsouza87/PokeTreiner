package com.github.hebertsouza87.pokeTreiner.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PokemonJson(Long id, String name, Integer number, Integer level) {
    public PokemonJson {
    }

    public PokemonJson(String name, Integer number) {
        this(null, name, number, 1);
    }

    public static PokemonJson fromModel(PokemonEntity model) {
        return new PokemonJson(model.getId(), model.getName(), model.getNumber(), model.getLevel());
    }

    public static List<PokemonJson> fromModel(List<PokemonEntity> pokemons) {
        if (pokemons == null) {
            return List.of();
        }
        return pokemons.stream().map(PokemonJson::fromModel).toList();
    }

    public PokemonEntity toModel() {
        return new PokemonEntity(name, number);
    }
}
