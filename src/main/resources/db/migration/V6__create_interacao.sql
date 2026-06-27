CREATE TABLE interacao (

    id UUID PRIMARY KEY,

    relacionamento_id UUID NOT NULL,

    tipo VARCHAR(50) NOT NULL,

    resultado VARCHAR(30),

    titulo VARCHAR(200) NOT NULL,

    descricao TEXT,

    data_interacao TIMESTAMP NOT NULL,

    proxima_acao TEXT,

    data_proxima_acao DATE,

    realizada_por VARCHAR(150),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_interacao_relacionamento
        FOREIGN KEY (relacionamento_id)
        REFERENCES relacionamento(id)
);