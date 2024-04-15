package com.github.hebertsouza87.pokeTreiner.application.repository;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinerRepo extends JpaRepository<TreinerEntity, Long> {
    TreinerEntity findByEmail(String email);
}