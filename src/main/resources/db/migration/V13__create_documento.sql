CREATE TABLE documento (

    id UUID PRIMARY KEY,

    relacionamento_id UUID NOT NULL,

    titulo VARCHAR(200) NOT NULL,

    tipo VARCHAR(50) NOT NULL,

    descricao TEXT,

    nome_arquivo VARCHAR(255) NOT NULL,

    caminho_arquivo VARCHAR(500),

    url_arquivo VARCHAR(500),

    versao INTEGER NOT NULL DEFAULT 1,

    data_documento DATE,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_documento_relacionamento
        FOREIGN KEY (relacionamento_id)
        REFERENCES relacionamento(id)
);