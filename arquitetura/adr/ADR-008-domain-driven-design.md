# ADR-008 — Domain-Driven Design como abordagem arquitetural

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas possui um domínio complexo, baseado em relacionamento institucional, inteligência de dados e gestão do conhecimento.

Era necessário definir uma abordagem arquitetural que privilegiasse o domínio do negócio.

---

# Decisão

O desenvolvimento do Atlas seguirá princípios inspirados em Domain-Driven Design (DDD).

As decisões arquiteturais deverão priorizar o modelo de domínio em detrimento de estruturas puramente técnicas.

---

# Princípios adotados

- Linguagem ubíqua.
- Agregados.
- Entidades.
- Objetos de Valor.
- Separação por contexto.
- Evolução orientada ao domínio.

---

# Consequências

## Positivas

- Código mais aderente ao negócio.
- Facilidade de evolução.
- Melhor comunicação entre especialistas e desenvolvedores.

## Negativas

- Curva de aprendizado maior.
- Exige disciplina de modelagem.

---

# Situação futura

Novos módulos deverão preservar a coerência do domínio e da linguagem estabelecida pelo Atlas.