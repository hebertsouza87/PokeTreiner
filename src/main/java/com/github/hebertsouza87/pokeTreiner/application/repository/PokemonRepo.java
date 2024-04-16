package com.github.hebertsouza87.pokeTreiner.application.repository;

import com.github.hebertsouza87.pokeTreiner.application.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepo extends JpaRepository<PokemonEntity, Long> {
    List<PokemonEntity> findByTreinerId(Long trainerId);
}
