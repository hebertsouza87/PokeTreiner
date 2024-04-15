package com.github.hebertsouza87.pokeTreiner.application.model;

import com.github.hebertsouza87.pokeTreiner.domain.model.Treiner;

public class TreinerJson {

    private Long id;
    private String name;
    private String email;
    private Integer age;

    public TreinerJson() {
    }

    public TreinerJson(Treiner register) {
        id = register.getId();
        name = register.getName();
        email = register.getEmail();
        age = register.getAge();
    }

    public Treiner toModel() {
        return new Treiner(name, email, age);
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
}
