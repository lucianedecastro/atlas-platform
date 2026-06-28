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
| ADR-005 | Relacionamento como agregado principal | ✅ Aceita (com atualização em 2026-06-28) |
| ADR-006 | Score de Afinidade calculado por VIEW | ✅ Aceita |
| ADR-007 | Documentos armazenados fora do banco | ✅ Aceita |
| ADR-008 | Domain-Driven Design como abordagem arquitetural | ✅ Aceita |
| ADR-009 | Docker como ambiente padrão de desenvolvimento | ⏳ Planejada |
| ADR-010 | Camada de Storage desacoplada | ⏳ Planejada |

---

# Convenções

Cada ADR deverá conter:

- Contexto
- Problema
- Decisão
- Consequências
- Alternativas consideradas
- Status

---

# Status possíveis

- Proposta
- Aceita
- Substituída
- Obsoleta

---

As ADRs fazem parte da documentação oficial do Atlas e devem ser atualizadas sempre que uma decisão arquitetural relevante for tomada.