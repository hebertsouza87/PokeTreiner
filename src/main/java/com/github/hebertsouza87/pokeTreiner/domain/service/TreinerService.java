package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.domain.model.Treiner;
import com.github.hebertsouza87.pokeTreiner.domain.repository.TreinerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreinerService {

    private final TreinerRepo repo;

    @Autowired
    public TreinerService(TreinerRepo repo) {
        this.repo = repo;
    }

    public Treiner register(Treiner treiner) {
        validateTreiner(treiner);
        return repo.save(treiner);
    }

    private void validateTreiner(Treiner treiner) {
        if (repo.findByEmail(treiner.getEmail()) != null) {
            throw new IllegalArgumentException("Treiner with email " + treiner.getEmail() + " already exists");
        }

        if (treiner.getAge() < 10) {
            throw new IllegalArgumentException("Treiner must be at least 10 years old");
        }
    }

    public Treiner updateTreiner(Long id, Treiner model) {
        Treiner treiner = findById(id);
        treiner.setName(model.getName());
        treiner.setEmail(model.getEmail());
        treiner.setAge(model.getAge());
        return repo.save(treiner);
    }

    public void deleteTreiner(Long id) {
        repo.delete(findById(id));
    }

    public Treiner findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Treiner with id " + id + " not found"));
    }
}
