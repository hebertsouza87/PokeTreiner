package com.github.hebertsouza87.pokeTreiner.application.repository;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreinerRepo extends JpaRepository<TreinerEntity, Long> {

    @Query("SELECT t FROM TreinerEntity t JOIN FETCH t.pokemons WHERE t.id = :id")
    Optional<TreinerEntity> findByIdWithPokemons(@Param("id") Long id);

    TreinerEntity findByEmail(String email);
}