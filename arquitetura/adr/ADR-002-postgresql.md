# ADR-002 — PostgreSQL como banco oficial

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas necessita de um banco de dados relacional robusto, aderente aos padrões SQL, com excelente suporte a integridade referencial, consultas complexas e recursos avançados para evolução futura.

---

# Decisão

Foi adotado o PostgreSQL como banco de dados oficial do Atlas.

---

# Motivos

- Software livre.
- Excelente aderência ao padrão SQL.
- Suporte nativo a UUID.
- Views e Materialized Views.
- JSONB.
- Full Text Search.
- Extensões como pgvector.
- Excelente integração com Spring Boot.
- Grande maturidade e estabilidade.

---

# Consequências

## Positivas

- Plataforma preparada para crescimento.
- Recursos avançados para inteligência de dados.
- Facilidade de integração com IA futuramente.

## Negativas

- Curva de aprendizado maior que bancos embarcados.
- Dependência de ambiente PostgreSQL para desenvolvimento.

---

# Situação futura

O PostgreSQL continuará sendo o banco oficial do Atlas enquanto atender às necessidades do domínio.