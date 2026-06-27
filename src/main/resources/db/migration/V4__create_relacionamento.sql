CREATE TABLE relacionamento (

    id UUID PRIMARY KEY,

    projeto_id UUID NOT NULL,

    organizacao_id UUID NOT NULL,

    status VARCHAR(50) NOT NULL,

    origem VARCHAR(100),

    objetivo VARCHAR(100),

    prioridade VARCHAR(20) NOT NULL DEFAULT 'MEDIA',

    responsavel VARCHAR(150),

    observacoes TEXT,

    data_inicio DATE NOT NULL,

    data_encerramento DATE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_relacionamento_projeto
        FOREIGN KEY (projeto_id)
        REFERENCES projeto(id),

    CONSTRAINT fk_relacionamento_organizacao
        FOREIGN KEY (organizacao_id)
        REFERENCES organizacao(id)
);