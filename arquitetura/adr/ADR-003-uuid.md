# ADR-003 — UUID como chave primária

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas será uma plataforma multiusuário, preparada para integração com APIs, armazenamento distribuído e evolução para ambientes em nuvem.

---

# Decisão

Todas as entidades persistentes utilizarão UUID como chave primária.

---

# Motivos

- Evita colisões entre ambientes.
- Facilita sincronizações futuras.
- Dificulta enumeração de registros.
- Melhor compatibilidade com microsserviços.
- Excelente suporte no PostgreSQL.

---

# Consequências

## Positivas

- Identificadores globalmente únicos.
- Maior segurança na exposição das APIs.
- Independência entre ambientes.

## Negativas

- Menor legibilidade para humanos.
- Índices ligeiramente maiores.

---

# Situação futura

UUID permanecerá como padrão para todas as entidades persistentes do Atlas.