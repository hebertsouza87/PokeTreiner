package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import com.github.hebertsouza87.pokeTreiner.domain.service.PokemonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Pokémon", description = "Pokémon from a treiner API")
@RestController
@RequestMapping("/treiner/{treinerId}/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public List<PokemonJson> getPokemonsByTrainerId(@PathVariable("treinerId") Long trainerId) {
        return PokemonJson.fromModel(pokemonService.getPokemonsByTrainerId(trainerId));
    }

    @PostMapping()
    public PokemonJson catchPokemon(@PathVariable Long treinerId, @RequestBody PokemonJson pokemon) {
        return PokemonJson.fromModel(pokemonService.addPokemonToTreiner(treinerId, pokemon.number()));
    }


    @PutMapping("/{pokemonId}/levelUp")
    public PokemonJson pokemonLevelUp(@PathVariable("pokemonId") Long pokemonId) {
        return PokemonJson.fromModel(pokemonService.levelUp(pokemonId));
    }

    @DeleteMapping("/{pokemonId}")
    public void releasePokemon(@PathVariable("pokemonId") Long pokemonId) {
        pokemonService.releasePokemon(pokemonId);
    }
}