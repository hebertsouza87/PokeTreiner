package com.github.hebertsouza87.pokeTreiner.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreinerEntityTest {

    private TreinerEntity treiner;

    @BeforeEach
    void setUp() {
        treiner = new TreinerEntity("Ash", "ash@example.com", 20);
    }

    @Test
    void getName() {
        assertEquals("Ash", treiner.getName());
    }

    @Test
    void setName() {
        treiner.setName("Brock");
        assertEquals("Brock", treiner.getName());
    }

    @Test
    void getEmail() {
        assertEquals("ash@example.com", treiner.getEmail());
    }

    @Test
    void setEmail() {
        treiner.setEmail("brock@example.com");
        assertEquals("brock@example.com", treiner.getEmail());
    }

    @Test
    void getAge() {
        assertEquals(20, treiner.getAge());
    }

    @Test
    void setAge() {
        treiner.setAge(30);
        assertEquals(30, treiner.getAge());
    }
}