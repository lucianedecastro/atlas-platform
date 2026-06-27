CREATE TABLE organizacao (
    id UUID PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150),

    cnpj VARCHAR(18),

    tipo VARCHAR(50) NOT NULL,
    status_relacionamento VARCHAR(50) NOT NULL,

    segmento VARCHAR(100),

    site VARCHAR(255),

    descricao TEXT,

    utiliza_lei_incentivo BOOLEAN NOT NULL DEFAULT FALSE,

    possui_instituto BOOLEAN NOT NULL DEFAULT FALSE,

    observacoes TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP
);