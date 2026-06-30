# Architecture Decision Records (ADR)

## Objetivo

Este diretório contém todas as decisões arquiteturais relevantes do Atlas.

Cada ADR registra o contexto, a decisão tomada, suas consequências e os motivos que levaram à escolha.

O objetivo é preservar o conhecimento arquitetural do projeto ao longo de sua evolução.

---

# ADRs

| ADR | Título | Status |
|------|--------|--------|
| ADR-001 | Flyway como ferramenta oficial de migração | ✅ Aceita |
| ADR-002 | PostgreSQL como banco oficial | ✅ Aceita |
| ADR-003 | UUID como chave primária | ✅ Aceita |
| ADR-004 | Mermaid como padrão de diagramas | ✅ Aceita |
| ADR-005 | Relacionamento como agregado principal | ✅ Aceita (atualizada 2026-06-28) |
| ADR-006 | Score de Afinidade calculado por VIEW | ✅ Aceita |
| ADR-007 | Documentos armazenados fora do banco | ✅ Aceita |
| ADR-008 | Domain-Driven Design como abordagem arquitetural | ✅ Aceita |
| ADR-009 | Docker como ambiente padrão de desenvolvimento | ✅ Aceita |
| ADR-010 | Camada de Storage desacoplada do banco | ✅ Aceita — implementação pendente |
| ADR-011 | Mappers estáticos em vez de MapStruct | ✅ Aceita |
| ADR-012 | OportunidadeCandidata como pipeline de staging | ✅ Aceita |
| ADR-013 | Inteligência calculada on-demand | ✅ Aceita |

---

# Convenções

Cada ADR contém:

- Contexto
- Decisão
- Motivos
- Consequências (positivas e negativas)
- Situação futura

---

# Status possíveis

- **Proposta** — em discussão, ainda não decidida
- **Aceita** — decisão tomada e em vigor
- **Substituída** — substituída por outra ADR (referencia a nova)
- **Obsoleta** — não se aplica mais ao projeto

---

# Quando criar uma nova ADR

Uma nova ADR deve ser criada sempre que uma decisão técnica relevante for tomada — especialmente quando:

- A decisão afeta mais de um domínio.
- A decisão poderia ter sido tomada de outra forma e a razão da escolha não é óbvia.
- A decisão tem consequências de longo prazo na arquitetura.
- A decisão envolve descarte de uma alternativa considerada.

As ADRs fazem parte da documentação oficial do Atlas e devem ser criadas antes ou imediatamente após a implementação da decisão.