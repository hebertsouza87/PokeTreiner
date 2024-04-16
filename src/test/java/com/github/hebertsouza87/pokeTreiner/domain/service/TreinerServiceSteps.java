package com.github.hebertsouza87.pokeTreiner.domain.service;

import com.github.hebertsouza87.pokeTreiner.application.entity.TreinerEntity;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component
public class TreinerServiceSteps {

    @Autowired
    private TreinerService service;
    private TreinerEntity treiner;

    @Given("a treiner with name $name, email $email and age $age")
    public void givenATreiner(String name, String email, int age) {
        treiner = new TreinerEntity(name, email, age);
    }

    @When("the treiner is registered")
    public void whenTheTreinerIsRegistered() {
        service.register(treiner);
    }

    @Then("the treiner should be saved with name $name, email $email and age $age")
    public void thenTheTreinerShouldBeSaved(String name, String email, int age) {
        TreinerEntity savedTreiner = service.findById(treiner.getId());
        assertEquals(name, savedTreiner.getName());
        assertEquals(email, savedTreiner.getEmail());
        assertEquals(age, savedTreiner.getAge());
    }
}