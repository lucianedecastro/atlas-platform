# ADR-001 — Flyway como ferramenta oficial de migração

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas utiliza PostgreSQL como banco de dados relacional.

Durante o desenvolvimento do projeto tornou-se necessário adotar uma estratégia que garantisse:

- versionamento do esquema do banco;
- rastreabilidade das alterações;
- facilidade de implantação em diferentes ambientes;
- integração ao ciclo de desenvolvimento da aplicação.

---

# Decisão

Foi adotado o Flyway como ferramenta oficial de gerenciamento de migrations.

Todas as alterações estruturais do banco de dados deverão ser realizadas exclusivamente por meio de migrations versionadas.

Nenhuma alteração manual será considerada parte oficial da arquitetura.

---

# Consequências

## Positivas

- Histórico completo da evolução do banco.
- Facilidade de implantação em novos ambientes.
- Controle de versão integrado ao Git.
- Possibilidade de rollback lógico através de novas migrations.
- Maior segurança durante deploys.

## Negativas

- Migrations aplicadas não devem ser alteradas.
- Mudanças estruturais exigem novas migrations.
- Requer disciplina da equipe.

---

# Diretrizes

- Uma migration deve representar apenas uma mudança de domínio.
- Toda migration deve possuir numeração sequencial.
- Alterações em produção serão realizadas exclusivamente via Flyway.
- Antes da implementação da migration, o DER deverá ser atualizado.

---

# Situação futura

O Flyway continuará sendo a única fonte oficial de evolução do esquema do banco de dados.