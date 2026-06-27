CREATE TABLE organizacao_interesse (

    id UUID PRIMARY KEY,

    organizacao_id UUID NOT NULL,

    interesse_id UUID NOT NULL,

    peso SMALLINT NOT NULL,

    confianca SMALLINT NOT NULL DEFAULT 100,

    fonte VARCHAR(150),

    observacoes TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_oi_organizacao
        FOREIGN KEY (organizacao_id)
        REFERENCES organizacao(id),

    CONSTRAINT fk_oi_interesse
        FOREIGN KEY (interesse_id)
        REFERENCES interesse(id),

    CONSTRAINT uk_organizacao_interesse
        UNIQUE (organizacao_id, interesse_id),

    CONSTRAINT chk_peso
        CHECK (peso BETWEEN 1 AND 10),

    CONSTRAINT chk_confianca
        CHECK (confianca BETWEEN 0 AND 100)
);