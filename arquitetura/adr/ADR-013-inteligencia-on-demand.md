# ADR-013 — Inteligência calculada on-demand, sem persistência de resultados

## Status

Aceita

## Data

2026-06-29 (decisão tomada implicitamente durante Sprint 4, formalizada aqui)

---

# Contexto

O domínio de Inteligência do Atlas produz dois tipos de resultado:

1. **Score de Afinidade** — grau de aderência entre um projeto e uma organização, baseado em interesses compartilhados.
2. **Conexões Sugeridas** — lista de organizações recomendadas para um projeto, ordenadas por afinidade.

Era necessário decidir se esses resultados seriam:

- **Pré-computados e persistidos** (batch): calculados periodicamente por um job e armazenados em tabela para consulta rápida.
- **Calculados on-demand**: produzidos no momento da requisição, lendo diretamente dos dados existentes.

---

# Decisão

A inteligência do Atlas é calculada on-demand.

Nenhum resultado intermediário de score ou recomendação é armazenado em tabela separada.

O Score de Afinidade é calculado por uma VIEW PostgreSQL (`vw_score_afinidade`) — ver ADR-006.

As Conexões Sugeridas são calculadas pelo `InteligenciaService` no momento da requisição, lendo a view e os dados de Projeto, Organização e Interesse.

---

# Motivos

- No volume atual de dados, o custo de calcular on-demand é desprezível.
- Elimina a necessidade de jobs de background, agendamento e sincronização.
- Resultados sempre refletem o estado atual dos dados — sem lag de atualização.
- Menos infraestrutura para manter.
- A VIEW do PostgreSQL já provê uma camada eficiente de cálculo incremental.

---

# Consequências

## Positivas

- Implementação simples, sem scheduler nem worker.
- Dados sempre frescos.
- Fácil de evoluir o algoritmo: basta alterar a VIEW ou o Service.

## Negativas

- Com crescimento significativo do volume de dados, consultas on-demand podem degradar.
- Sem histórico de scores calculados anteriormente — não é possível ver como a afinidade evoluiu ao longo do tempo.

---

# Situação futura

Quando o volume de dados justificar, a arquitetura pode evoluir para um modelo híbrido: resultados pré-computados em Materialized View (PostgreSQL) com refresh periódico, sem necessidade de mudar a interface do `InteligenciaService` para os consumidores (frontend e demais domínios).

A migração para banco de grafos (mencionada em FOUNDATION.md) tornaria essa decisão revisável, dado que grafos nativos calculam conexões de forma muito mais eficiente em grandes volumes.