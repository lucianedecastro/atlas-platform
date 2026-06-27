CREATE TABLE contato (

    id UUID PRIMARY KEY,

    relacionamento_id UUID NOT NULL,

    nome VARCHAR(150) NOT NULL,

    cargo VARCHAR(150),

    email VARCHAR(200),

    telefone VARCHAR(30),

    linkedin VARCHAR(255),

    principal BOOLEAN NOT NULL DEFAULT FALSE,

    observacoes TEXT,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_contato_relacionamento
        FOREIGN KEY (relacionamento_id)
        REFERENCES relacionamento(id)
);