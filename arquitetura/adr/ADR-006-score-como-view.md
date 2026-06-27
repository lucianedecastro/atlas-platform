# ADR-006 — Score de Afinidade calculado por VIEW

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Score de Afinidade representa um valor derivado da combinação entre interesses, indicadores e demais atributos de projetos e organizações.

---

# Decisão

O Score não será armazenado em tabela.

Seu cálculo será realizado por meio de VIEW ou Materialized View no PostgreSQL.

---

# Motivos

- Evita inconsistências.
- Elimina duplicidade de dados.
- Permite atualização automática.
- Facilita evolução do algoritmo.

---

# Consequências

## Positivas

- Dados sempre atualizados.
- Maior consistência.
- Algoritmo desacoplado do armazenamento.

## Negativas

- Consultas mais complexas.
- Dependência de otimizações quando houver grande volume de dados.

---

# Situação futura

A evolução do algoritmo ocorrerá por novas versões da VIEW.