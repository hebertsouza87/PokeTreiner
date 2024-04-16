package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.gateway.PokeApiGateway;
import com.github.hebertsouza87.pokeTreiner.application.repository.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PokemonService {

    private final PokeApiGateway pokeApiGateway;
    private final PokemonRepo repo;
    private final Random random;

    @Autowired
    public PokemonService(PokeApiGateway pokeApiGateway, PokemonRepo repo) {
        this.pokeApiGateway = pokeApiGateway;
        this.repo = repo;
        this.random = new Random();
    }

    public PokemonEntity giveStarterPokemon(TreinerEntity treiner) {
        int pokemonNumber = random.nextInt(151) + 1;
        PokemonEntity pokemon = pokeApiGateway.getPokemon(pokemonNumber).toModel();
        pokemon.setNumber(pokemonNumber);
        pokemon.setTreiner(treiner);
        repo.save(pokemon);
        return pokemon;
    }
}