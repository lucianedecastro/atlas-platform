# ROADMAP

## Sprint 0

- Projeto Spring Boot
- Docker
- PostgreSQL
- Flyway
- Swagger
- BaseEntity
- Organização
- CRUD inicial

Status: ✅ Concluído

---

## Sprint 1

Refatoração da arquitetura

- DTO ✅
- ~~MapStruct~~ — **substituído por mappers estáticos escritos à mão** (`XxxMapper.toEntity()`/`toResponse()`), em todos os domínios. Decisão tomada durante a implementação, não formalizada em ADR ainda.
- Validation ✅ — Bean Validation (`@NotNull`, `@NotBlank`, `@Min`/`@Max`) em todos os DTOs de request
- Exceptions ✅ — `RecursoNaoEncontradoException` (404) e `IllegalArgumentException` (400) tratadas no `GlobalExceptionHandler`
- ResponseEntity ✅ — via `ApiResponse<T>` envelopando toda resposta de sucesso

Status: ✅ Concluído (com a divergência do MapStruct registrada acima)

---

## Sprint 2

Relacionamentos

- Contato — ⏳ apenas banco (V5), sem Java
- Interação — ⏳ apenas banco (V6), sem Java
- Documento — ✅ completo, incluindo exceção ao agregado de Relacionamento (ver ADR-005)
- Tarefa — ⏳ apenas banco (V8), sem Java

Status: 🔄 Em andamento (Documento adiantado fora de ordem; Contato, Interação e Tarefa pendentes)

---

## Sprint 3

Captação

- Oportunidade ✅ completo
- Pipeline — 🔄 parcial: visível no frontend como seção "Pipeline de relacionamento" no Perfil de Organização, baseado em `StatusRelacionamento` (ver GLOSSÁRIO.md)
- Dashboard — ⏳ não iniciado

Status: 🔄 Em andamento

---

## Sprint 4

Inteligência

- Indicadores — ⏳ não iniciado
- Afinidade ✅ completo (Interesse, OrganizacaoInteresse, ProjetoInteresse)
- Recomendações — 🔄 parcial: endpoint `/api/inteligencia/conexoes-sugeridas` funcional, sem tela de frontend dedicada ainda
- Score ✅ completo (`vw_score_afinidade`, ver ADR-006)

Status: 🔄 Em andamento

---

## Sprint 5

IA

- Sugestão de patrocinadores — 🔄 fundação completa: domínio `OportunidadeCandidata` (staging com proveniência, confiança e fluxo de aprovação/rejeição) está implementado e testado; falta o motor de busca/ingestão automática (ex.: SALIC, Lei de Incentivo ao Esporte) que alimentaria essa fila
- Sugestão de abordagem — ⏳ não iniciado
- Recomendações automáticas — 🔄 parcial, mesma base do item de Recomendações da Sprint 4

Status: 🔄 Em andamento (adiantado fora de ordem, antes da Sprint 2 estar completa)

---

## Observações

- O backend dos oito domínios principais (Organização, Projeto, Relacionamento, Documento, Oportunidade, OportunidadeCandidata, Interesse + junções, Inteligência) está completo e testado de ponta a ponta.
- Este roadmap não previa explicitamente o desenvolvimento de frontend. O trabalho iniciado nesta sessão (Vite + React + TypeScript, com a primeira tela — Perfil de Organização — funcional) não está mapeado em nenhuma sprint formal acima. Recomenda-se decidir se isso entra como sprint própria ou como trabalho transversal às sprints existentes.
- A entrega seguiu uma ordem diferente da sequência de sprints original, guiada pelas necessidades de cada conversa de arquitetura, não pela numeração das sprints.