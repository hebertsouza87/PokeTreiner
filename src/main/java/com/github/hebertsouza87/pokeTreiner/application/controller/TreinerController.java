package com.github.hebertsouza87.pokeTreiner.application.controller;

import com.github.hebertsouza87.pokeTreiner.application.model.TreinerJson;
import com.github.hebertsouza87.pokeTreiner.domain.service.TreinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treiner")
public class TreinerController {

    private final TreinerService treinerService;

    @Autowired
    public TreinerController(TreinerService treinerService) {
        this.treinerService = treinerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TreinerJson register(@RequestBody TreinerJson treiner) {
        return new TreinerJson(treinerService.register(treiner.toModel()));
    }

    @GetMapping("/{id}")
    public TreinerJson getTreiner(@PathVariable Long id) {
        return new TreinerJson(treinerService.findById(id));
    }

    @PutMapping("/{id}")
    public TreinerJson updateTreiner(@PathVariable Long id, @RequestBody TreinerJson treiner) {
        return new TreinerJson(treinerService.updateTreiner(id, treiner.toModel()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTreiner(@PathVariable Long id) {
        treinerService.deleteTreiner(id);
    }
}