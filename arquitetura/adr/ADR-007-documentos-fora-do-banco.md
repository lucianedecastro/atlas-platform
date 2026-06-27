# ADR-007 — Armazenamento de documentos fora do banco de dados

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas gerenciará documentos relacionados às atividades de captação, contratos, apresentações e demais registros institucionais.

---

# Decisão

Os arquivos físicos não serão armazenados no banco de dados.

A base armazenará apenas metadados e referências ao local de armazenamento.

---

# Motivos

- Melhor desempenho.
- Menor crescimento do banco.
- Compatibilidade com serviços de armazenamento em nuvem.
- Facilidade de backup.

---

# Consequências

## Positivas

- Banco mais leve.
- Escalabilidade.
- Independência da infraestrutura de armazenamento.

## Negativas

- Necessidade de gerenciamento do storage.

---

# Situação futura

A camada de armazenamento deverá ser desacoplada da aplicação.