CREATE TABLE IF NOT EXISTS JOGADORES (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
     telefone VARCHAR(255),
     codinome VARCHAR(255),
     grupo_codinome VARCHAR(255) NOT NULL,
     CONSTRAINT unique_codinome_grupo UNIQUE (codinome, grupo_codinome)
);