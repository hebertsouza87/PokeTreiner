package com.github.hebertsouza87.pokeTreiner.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;

import java.util.List;

public class TreinerJson {
    
    private Long id;
    private String name;
    private String email;
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PokemonJson> pokemons;

    public TreinerJson() {
    }

    public TreinerJson(TreinerEntity register) {
        id = register.getId();
        name = register.getName();
        email = register.getEmail();
        age = register.getAge();
        pokemons = PokemonJson.fromModel(register.getPokemons());
    }

    public TreinerEntity toModel() {
        return new TreinerEntity(name, email, age);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<PokemonJson> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonJson> pokemons) {
        this.pokemons = pokemons;
    }
}
