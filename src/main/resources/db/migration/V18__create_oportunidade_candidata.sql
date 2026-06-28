CREATE TABLE oportunidade_candidata (

    id UUID PRIMARY KEY,

    titulo VARCHAR(200) NOT NULL,

    tipo VARCHAR(50) NOT NULL,

    descricao TEXT,

    valor_estimado NUMERIC(15,2),

    data_limite DATE,

    fonte VARCHAR(150) NOT NULL,

    url_origem VARCHAR(500),

    confianca SMALLINT NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'PENDENTE',

    projeto_id UUID,

    organizacao_id UUID,

    organizacao_nome_sugerido VARCHAR(150),

    observacoes TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_oportunidade_candidata_projeto
        FOREIGN KEY (projeto_id)
        REFERENCES projeto(id),

    CONSTRAINT fk_oportunidade_candidata_organizacao
        FOREIGN KEY (organizacao_id)
        REFERENCES organizacao(id),

    CONSTRAINT chk_oportunidade_candidata_confianca
        CHECK (confianca BETWEEN 0 AND 100)

);