# ADR-005 — Relacionamento como agregado principal

## Status

Aceita

## Data

2026-06-27

---

# Contexto

Durante a modelagem do domínio observou-se que o relacionamento institucional representa o principal ativo do Atlas.

Contato, interação, documentos e oportunidades não existem isoladamente, mas sempre associados ao vínculo entre um projeto e uma organização.

---

# Decisão

A entidade Relacionamento será o agregado central do domínio operacional.

As seguintes entidades dependerão diretamente dela:

- Contato
- Interação
- Documento
- Oportunidade

Tarefas permanecerão vinculadas às oportunidades.

---

# Consequências

## Positivas

- Modelo aderente ao domínio.
- Histórico institucional preservado.
- Maior flexibilidade para múltiplos projetos.

## Negativas

- Estrutura relacional mais sofisticada.

---

# Atualização — 2026-06-28

Durante a modelagem do domínio `Documento`, identificou-se a necessidade de representar materiais que precedem a existência formal de um Relacionamento — por exemplo, o material institucional de um projeto, ou uma proposta personalizada direcionada a um potencial patrocinador com quem ainda não existe negociação aberta.

Por esse motivo, `Documento` passa a ser uma **exceção controlada** à regra geral deste ADR:

- Quando vinculado a um `Relacionamento`, segue a regra original — documento de negociação.
- Quando não vinculado a um `Relacionamento`, deve estar vinculado a pelo menos um `Projeto` e/ou uma `Organização` — documento institucional ou proposta direcionada.
- As duas formas são mutuamente exclusivas: um documento nunca possui `Relacionamento` e `Projeto`/`Organização` simultaneamente.

`Contato`, `Interação` e `Oportunidade` permanecem sem exceção, dependendo exclusivamente de `Relacionamento`, conforme a decisão original.

## Consequência adicional

- `Documento` exige uma camada extra de validação de contexto (negociação vs. institucional) que as demais entidades dependentes de Relacionamento não precisam.

---

# Situação futura

Novas funcionalidades deverão considerar Relacionamento como centro do domínio operacional, exceto nos casos já formalmente registrados como exceção controlada (ver seção "Atualização").