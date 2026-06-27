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

# Situação futura

Novas funcionalidades deverão considerar Relacionamento como centro do domínio operacional.