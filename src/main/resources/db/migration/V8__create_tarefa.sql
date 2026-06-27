CREATE TABLE tarefa (

    id UUID PRIMARY KEY,

    oportunidade_id UUID NOT NULL,

    titulo VARCHAR(200) NOT NULL,

    descricao TEXT,

    status VARCHAR(30) NOT NULL DEFAULT 'PENDENTE',

    prioridade VARCHAR(20) NOT NULL DEFAULT 'MEDIA',

    prazo DATE,

    concluida_em TIMESTAMP,

    responsavel VARCHAR(150),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_tarefa_oportunidade
        FOREIGN KEY (oportunidade_id)
        REFERENCES oportunidade(id)
);