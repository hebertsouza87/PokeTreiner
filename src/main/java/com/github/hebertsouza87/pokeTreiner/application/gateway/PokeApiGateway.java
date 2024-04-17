package com.github.hebertsouza87.pokeTreiner.application.gateway;

import com.github.hebertsouza87.pokeTreiner.application.model.PokemonJson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiGateway {
    private static final Logger log = LoggerFactory.getLogger(PokeApiGateway.class);

    private RestTemplate restTemplate;
    private String apiUrl;

    @Value("${gateway.pokeapi.url}")
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "pokeApi", fallbackMethod = "getPokemonFallback")
    public PokemonJson getPokemon(int id) {
        try {
            ResponseEntity<PokemonJson> response = restTemplate
                    .getForEntity(apiUrl + "/pokemon/{id}", PokemonJson.class, id);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }

        } catch (Exception e) {
            log.error("Error occurred while calling PokeAPI: ", e);
        }

        return null;
    }

    public PokemonJson getPokemonFallback(int id, Throwable throwable) {
        log.error("Fallback method called due to: ", throwable);
        return new PokemonJson("pickachu", 25);
    }
}