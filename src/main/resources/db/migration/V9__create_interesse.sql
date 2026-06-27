CREATE TABLE interesse (

    id UUID PRIMARY KEY,

    nome VARCHAR(150) NOT NULL UNIQUE,

    categoria VARCHAR(100),

    descricao TEXT,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP

);