package com.github.hebertsouza87.pokeTreiner.application.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treiner")
public class TreinerController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String register() {
        return "TODO";
    }
}
