CREATE TABLE pokemon
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(15) NOT NULL,
    number     INT         NOT NULL,
    treiner_id BIGINT      NOT NULL,
    FOREIGN KEY (treiner_id) REFERENCES treiner (id)
);

CREATE SEQUENCE pokemon_seq START 1;

CREATE INDEX idx_pokemon_treiner_id ON pokemon (treiner_id);