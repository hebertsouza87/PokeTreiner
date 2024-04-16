package com.github.hebertsouza87.pokeTreiner.application.gateway;

import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiGateway {
    private static final Logger log = LoggerFactory.getLogger(PokeApiGateway.class);

    @Value("${gateway.pokeapi.url}")
    private String apiUrl;

    public PokemonJson getPokemon(int id) {
        try {
            ResponseEntity<PokemonJson> response = new RestTemplate()
                    .getForEntity(apiUrl + "/pokemon/{id}", PokemonJson.class, id);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("Error occurred while calling PokeAPI: ", e);
        }

        return null;
    }
}