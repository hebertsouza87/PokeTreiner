package com.github.hebertsouza87.pokeTreiner.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer level = 1;

    @ManyToOne
    @JoinColumn(name = "treiner_id", nullable = false)
    private TreinerEntity treiner;

    public PokemonEntity() {
    }

    public PokemonEntity(String name, Integer number) {
        this.name = name;
        this.number = number;

    }

    public PokemonEntity(Long id, String name, Integer number, Integer level) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public TreinerEntity getTreiner() {
        return treiner;
    }

    public void setTreiner(TreinerEntity treiner) {
        this.treiner = treiner;
    }
}
