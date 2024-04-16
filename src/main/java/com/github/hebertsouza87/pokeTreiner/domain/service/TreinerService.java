package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.repository.TreinerRepo;
import com.github.hebertsouza87.pokeTreiner.domain.exception.InvalidObjectException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreinerService {

    private final TreinerRepo repo;

    @Autowired
    public TreinerService(TreinerRepo repo) {
        this.repo = repo;
    }

    public TreinerEntity register(TreinerEntity treiner) {
        validateTreiner(treiner);
        return repo.save(treiner);
    }

    public void validateTreiner(TreinerEntity treiner) {
        if (repo.findByEmail(treiner.getEmail()) != null) {
            throw new NotFoundException("Treiner with email " + treiner.getEmail() + " already exists");
        }

        if (treiner.getAge() < 10) {
            throw new InvalidObjectException("Treiner must be at least 10 years old");
        }
    }

    public TreinerEntity updateTreiner(Long id, TreinerEntity model) {
        TreinerEntity treiner = findById(id);
        treiner.setName(model.getName());
        treiner.setEmail(model.getEmail());
        treiner.setAge(model.getAge());
        return repo.save(treiner);
    }

    public void deleteTreiner(Long id) {
        repo.delete(findById(id));
    }

    public TreinerEntity findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Treiner with id " + id + " not found"));
    }
}