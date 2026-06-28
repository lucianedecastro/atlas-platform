ALTER TABLE documento
    ALTER COLUMN relacionamento_id DROP NOT NULL,
    ADD COLUMN projeto_id UUID,
    ADD COLUMN organizacao_id UUID;

ALTER TABLE documento
    ADD CONSTRAINT fk_documento_projeto
        FOREIGN KEY (projeto_id)
        REFERENCES projeto(id),

    ADD CONSTRAINT fk_documento_organizacao
        FOREIGN KEY (organizacao_id)
        REFERENCES organizacao(id),

    ADD CONSTRAINT chk_documento_contexto
        CHECK (
            (relacionamento_id IS NOT NULL AND projeto_id IS NULL AND organizacao_id IS NULL)
            OR
            (relacionamento_id IS NULL AND (projeto_id IS NOT NULL OR organizacao_id IS NOT NULL))
        );