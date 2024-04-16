package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.gateway.PokeApiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PokemonService {

    private final PokeApiGateway pokeApiGateway;
    private final Random random;

    @Autowired
    public PokemonService(PokeApiGateway pokeApiGateway) {
        this.pokeApiGateway = pokeApiGateway;
        this.random = new Random();
    }

    public String getRandomPokemonName() {
        int randomId = random.nextInt(151) + 1;
        return pokeApiGateway.getPokemonName(randomId);
    }
}