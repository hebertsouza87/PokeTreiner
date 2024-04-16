package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import com.github.hebertsouza87.pokeTreiner.application.repository.TreinerRepo;
import com.github.hebertsouza87.pokeTreiner.domain.exception.InvalidObjectException;
import com.github.hebertsouza87.pokeTreiner.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TreinerServiceTest {

    @Mock
    private TreinerRepo repo;

    private TreinerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TreinerService(repo);
    }

    @Test
    void register() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@example.com", 20);

        when(repo.findByEmail(treiner.getEmail())).thenReturn(null);
        when(repo.save(any(TreinerEntity.class))).thenReturn(treiner);

        TreinerEntity registeredTreiner = service.register(treiner);

        assertEquals(treiner, registeredTreiner);
    }

    @Test
    void registerWithExistingEmail() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@example.com", 20);

        when(repo.findByEmail(treiner.getEmail())).thenReturn(treiner);

        assertThrows(NotFoundException.class, () -> service.register(treiner));
    }

    @Test
    void registerWithInvalidAge() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@example.com", 9);

        assertThrows(InvalidObjectException.class, () -> service.register(treiner));
    }

    @Test
    void updateTreiner() {
        TreinerEntity existingTreiner = new TreinerEntity("Ash", "ash@example.com", 20);
        TreinerEntity updatedTreiner = new TreinerEntity("Ash Ketchum", "ash@example.com", 21);

        when(repo.findById(1L)).thenReturn(Optional.of(existingTreiner));
        when(repo.save(any(TreinerEntity.class))).thenReturn(updatedTreiner);

        TreinerEntity result = service.updateTreiner(1L, updatedTreiner);

        assertEquals(updatedTreiner, result);
    }

    @Test
    void updateNonExistingTreiner() {
        TreinerEntity treiner = new TreinerEntity("Ash", "ash@example.com", 20);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateTreiner(1L, treiner));
    }

    @Test
    void deleteTreiner() {
        TreinerEntity existingTreiner = new TreinerEntity("Ash", "ash@example.com", 20);

        when(repo.findById(1L)).thenReturn(Optional.of(existingTreiner));

        service.deleteTreiner(1L);

        verify(repo, times(1)).delete(existingTreiner);
    }

    @Test
    void deleteNonExistingTreiner() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteTreiner(1L));
    }

    @Test
    void findById() {
        TreinerEntity existingTreiner = new TreinerEntity("Ash", "ash@example.com", 20);

        when(repo.findById(1L)).thenReturn(Optional.of(existingTreiner));

        TreinerEntity result = service.findById(1L);

        assertEquals(existingTreiner, result);
    }

    @Test
    void findNonExistingTreinerById() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }
}