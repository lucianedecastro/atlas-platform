CREATE TABLE projeto_interesse (

    id UUID PRIMARY KEY,

    projeto_id UUID NOT NULL,

    interesse_id UUID NOT NULL,

    peso SMALLINT NOT NULL,

    criticidade SMALLINT NOT NULL DEFAULT 3,

    CHECK (criticidade BETWEEN 1 AND 5),

    observacoes TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_pi_projeto
        FOREIGN KEY (projeto_id)
        REFERENCES projeto(id),

    CONSTRAINT fk_pi_interesse
        FOREIGN KEY (interesse_id)
        REFERENCES interesse(id),

    CONSTRAINT uk_projeto_interesse
        UNIQUE (projeto_id, interesse_id),

    CONSTRAINT chk_pi_peso
        CHECK (peso BETWEEN 1 AND 10)
);