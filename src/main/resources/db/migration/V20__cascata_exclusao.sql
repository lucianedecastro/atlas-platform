ALTER TABLE relacionamento DROP CONSTRAINT fk_relacionamento_projeto;
ALTER TABLE relacionamento ADD CONSTRAINT fk_relacionamento_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE;

ALTER TABLE relacionamento DROP CONSTRAINT fk_relacionamento_organizacao;
ALTER TABLE relacionamento ADD CONSTRAINT fk_relacionamento_organizacao
    FOREIGN KEY (organizacao_id) REFERENCES organizacao(id) ON DELETE CASCADE;

ALTER TABLE contato DROP CONSTRAINT fk_contato_relacionamento;
ALTER TABLE contato ADD CONSTRAINT fk_contato_relacionamento
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id) ON DELETE CASCADE;

ALTER TABLE interacao DROP CONSTRAINT fk_interacao_relacionamento;
ALTER TABLE interacao ADD CONSTRAINT fk_interacao_relacionamento
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id) ON DELETE CASCADE;

ALTER TABLE oportunidade DROP CONSTRAINT fk_oportunidade_relacionamento;
ALTER TABLE oportunidade ADD CONSTRAINT fk_oportunidade_relacionamento
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id) ON DELETE CASCADE;

ALTER TABLE tarefa DROP CONSTRAINT fk_tarefa_oportunidade;
ALTER TABLE tarefa ADD CONSTRAINT fk_tarefa_oportunidade
    FOREIGN KEY (oportunidade_id) REFERENCES oportunidade(id) ON DELETE CASCADE;

ALTER TABLE documento DROP CONSTRAINT fk_documento_relacionamento;
ALTER TABLE documento ADD CONSTRAINT fk_documento_relacionamento
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id) ON DELETE CASCADE;

ALTER TABLE documento DROP CONSTRAINT fk_documento_projeto;
ALTER TABLE documento ADD CONSTRAINT fk_documento_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE;

ALTER TABLE documento DROP CONSTRAINT fk_documento_organizacao;
ALTER TABLE documento ADD CONSTRAINT fk_documento_organizacao
    FOREIGN KEY (organizacao_id) REFERENCES organizacao(id) ON DELETE CASCADE;

ALTER TABLE organizacao_interesse DROP CONSTRAINT fk_oi_organizacao;
ALTER TABLE organizacao_interesse ADD CONSTRAINT fk_oi_organizacao
    FOREIGN KEY (organizacao_id) REFERENCES organizacao(id) ON DELETE CASCADE;

ALTER TABLE organizacao_interesse DROP CONSTRAINT fk_oi_interesse;
ALTER TABLE organizacao_interesse ADD CONSTRAINT fk_oi_interesse
    FOREIGN KEY (interesse_id) REFERENCES interesse(id) ON DELETE CASCADE;

ALTER TABLE projeto_interesse DROP CONSTRAINT fk_pi_projeto;
ALTER TABLE projeto_interesse ADD CONSTRAINT fk_pi_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE;

ALTER TABLE projeto_interesse DROP CONSTRAINT fk_pi_interesse;
ALTER TABLE projeto_interesse ADD CONSTRAINT fk_pi_interesse
    FOREIGN KEY (interesse_id) REFERENCES interesse(id) ON DELETE CASCADE;

ALTER TABLE oportunidade_candidata DROP CONSTRAINT fk_oportunidade_candidata_projeto;
ALTER TABLE oportunidade_candidata ADD CONSTRAINT fk_oportunidade_candidata_projeto
    FOREIGN KEY (projeto_id) REFERENCES projeto(id) ON DELETE CASCADE;

ALTER TABLE oportunidade_candidata DROP CONSTRAINT fk_oportunidade_candidata_organizacao;
ALTER TABLE oportunidade_candidata ADD CONSTRAINT fk_oportunidade_candidata_organizacao
    FOREIGN KEY (organizacao_id) REFERENCES organizacao(id) ON DELETE CASCADE;