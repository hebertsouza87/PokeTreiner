package com.github.hebertsouza87.pokeTreiner.domain.repository;

import com.github.hebertsouza87.pokeTreiner.domain.model.Treiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinerRepo extends JpaRepository<Treiner, Long> {
    Treiner findByEmail(String email);
}
