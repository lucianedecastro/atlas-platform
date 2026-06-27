CREATE TABLE oportunidade (

    id UUID PRIMARY KEY,

    relacionamento_id UUID NOT NULL,

    titulo VARCHAR(200) NOT NULL,

    tipo VARCHAR(50) NOT NULL,

    status VARCHAR(50) NOT NULL,

    valor_solicitado NUMERIC(15,2),

    valor_aprovado NUMERIC(15,2),

    probabilidade INTEGER NOT NULL DEFAULT 0,

    data_prevista DATE,

    data_fechamento DATE,

    descricao TEXT,

    observacoes TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_oportunidade_relacionamento
        FOREIGN KEY (relacionamento_id)
        REFERENCES relacionamento(id),

    CONSTRAINT chk_probabilidade
        CHECK (probabilidade BETWEEN 0 AND 100)
);