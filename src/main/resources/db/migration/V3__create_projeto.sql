CREATE TABLE projeto (

    id UUID PRIMARY KEY,

    nome VARCHAR(200) NOT NULL,

    descricao TEXT,

    categoria VARCHAR(100),

    status VARCHAR(50) NOT NULL,

    proponente VARCHAR(200),

    lei_incentivo BOOLEAN NOT NULL DEFAULT FALSE,

    orcamento_previsto NUMERIC(15,2),

    valor_captado NUMERIC(15,2) DEFAULT 0,

    data_inicio DATE,

    data_fim DATE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP

);