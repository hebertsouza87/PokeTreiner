CREATE TABLE treiner
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    age   INTEGER     NOT NULL
);

CREATE SEQUENCE treiner_seq START 1;

CREATE INDEX idx_treiner_email ON treiner (email);