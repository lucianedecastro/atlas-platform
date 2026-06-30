# ADR-011 — Mappers estáticos em vez de MapStruct

## Status

Aceita

## Data

2026-06-29 (decisão tomada durante Sprint 1, formalizada aqui)

---

# Contexto

Durante a Sprint 1, ao refatorar a arquitetura de DTOs, o Atlas precisava de uma estratégia para converter entidades JPA em DTOs de resposta e DTOs de request em entidades.

MapStruct foi a alternativa inicialmente considerada por ser a abordagem mais comum em projetos Spring Boot.

---

# Decisão

O Atlas utiliza mappers estáticos escritos à mão, organizados como classes com métodos estáticos `toEntity()` e `toResponse()`.

Exemplo: `RelacionamentoMapper.toEntity(request, projeto, organizacao)` e `RelacionamentoMapper.toResponse(relacionamento)`.

MapStruct foi descartado.

---

# Motivos

- Mappers manuais são completamente explícitos — sem magia de geração de código.
- Facilitam depuração: o que está acontecendo é lido diretamente no código.
- Permitem lógica condicional e transformações complexas sem anotações especiais.
- Eliminam a dependência de processador de anotações (annotation processing) na build.
- Menor curva de aprendizado para quem entra no projeto.
- Suficientes para o volume de entidades do Atlas.

---

# Consequências

## Positivas

- Código de mapeamento totalmente legível e rastreável.
- Zero dependência externa para mapeamento.
- Facilidade de adicionar lógica de negócio dentro do mapper quando necessário.

## Negativas

- Código mais verboso para domínios com muitos campos.
- Alterações nos campos das entidades exigem atualização manual nos mappers.
- Sem geração automática: risco de esquecer campos novos.

---

# Convenção adotada

Cada domínio possui sua própria classe `XxxMapper` no pacote `mapper`, com os métodos:

- `toEntity(request, dependências...)` — converte DTO de request em entidade
- `toResponse(entity)` — converte entidade em DTO de resposta
- `atualizar(entity, request)` — aplica um DTO de atualização sobre uma entidade existente

---

# Situação futura

A abordagem de mappers estáticos permanecerá enquanto o volume de entidades e a complexidade dos mapeamentos não justificarem a adoção de uma solução de geração automática.