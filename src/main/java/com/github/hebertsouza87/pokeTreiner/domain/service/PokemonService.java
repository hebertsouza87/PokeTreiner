package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.gateway.PokeApiGateway;
import com.github.hebertsouza87.pokeTreiner.application.repository.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PokemonService {

    public static final int MAX_POKEMONS_PER_TRAINER = 6;
    private final PokeApiGateway pokeApiGateway;
    private final PokemonRepo repo;
    private final TreinerService treinerService;
    private final Random random;

    @Autowired
    public PokemonService(PokeApiGateway pokeApiGateway, PokemonRepo repo, TreinerService treinerService) {
        this.pokeApiGateway = pokeApiGateway;
        this.treinerService = treinerService;
        this.repo = repo;
        this.random = new Random();
    }

    public PokemonEntity giveStarterPokemon(TreinerEntity treiner) {
        int pokemonNumber = random.nextInt(151) + 1;
        return addPokemonToTreiner(treiner, pokemonNumber);
    }

    private PokemonEntity addPokemonToTreiner(TreinerEntity treiner, Integer pokemonNumber) {
        if (treiner.getPokemons().size() >= MAX_POKEMONS_PER_TRAINER) {
            throw new IllegalArgumentException("Treiner already has 6 pokemons");
        }

        PokemonEntity pokemon = pokeApiGateway.getPokemon(pokemonNumber).toModel();
        pokemon.setNumber(pokemonNumber);
        pokemon.setTreiner(treiner);
        repo.save(pokemon);
        return pokemon;
    }

    public PokemonEntity addPokemonToTreiner(Long treinerId, Integer pokemonNumber) {
        return addPokemonToTreiner(treinerService.findByIdWithPokemons(treinerId), pokemonNumber);
    }

    public List<PokemonEntity> getPokemonsByTrainerId(Long trainerId) {
        return repo.findByTreinerId(trainerId);
    }

    public PokemonEntity levelUp(Long pokemonId) {
        PokemonEntity pokemon = getPokemonById(pokemonId);
        pokemon.setLevel(pokemon.getLevel() + 1);
        repo.save(pokemon);
        return pokemon;
    }

    public void releasePokemon(Long pokemonId) {
        PokemonEntity pokemon = getPokemonById(pokemonId);
        repo.delete(pokemon);
    }

    private PokemonEntity getPokemonById(Long pokemonId) {
        Optional<PokemonEntity> pokemon = repo.findById(pokemonId);
        if (pokemon.isEmpty()) {
            throw new IllegalArgumentException("Pokemon not found");
        }
        return pokemon.orElse(null);
    }
}