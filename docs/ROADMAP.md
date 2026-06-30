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
- ~~MapStruct~~ — **substituído por mappers estáticos escritos à mão** (`XxxMapper.toEntity()` / `toResponse()`), em todos os domínios. Formalizado em ADR-011.
- Validation ✅ — Bean Validation (`@NotNull`, `@NotBlank`, `@Min`/`@Max`) em todos os DTOs de request
- Exceptions ✅ — `RecursoNaoEncontradoException` (404) e `IllegalArgumentException` (400) tratadas no `GlobalExceptionHandler`
- ResponseEntity ✅ — via `ApiResponse<T>` envelopando toda resposta de sucesso

Status: ✅ Concluído

---

## Sprint 2

Relacionamentos

- Contato ✅ — backend completo (V5 + Java: entity, DTO, mapper, service, controller)
- Interação ✅ — backend completo (V6 + Java)
- Documento ✅ — completo, incluindo exceção controlada ao agregado de Relacionamento (ver ADR-005, atualização 2026-06-28)
- Tarefa ✅ — backend completo (V8 + Java, vinculada a Oportunidade)

Status: ✅ Concluído

---

## Sprint 3

Captação

- Oportunidade ✅ — completo
- Pipeline ✅ — visível no frontend como seção "Pipeline de relacionamento" no Perfil de Organização, baseado em `StatusRelacionamento`
- Dashboard — ⏳ não iniciado

Status: 🔄 Em andamento

---

## Sprint 4

Inteligência

- Indicadores — ⏳ não iniciado
- Afinidade ✅ — completo (Interesse, OrganizacaoInteresse, ProjetoInteresse)
- Recomendações — 🔄 parcial: endpoint `/api/inteligencia/conexoes-sugeridas` funcional, sem tela de frontend dedicada ainda
- Score ✅ — completo (`vw_score_afinidade`, calculada on-demand via VIEW, ver ADR-006 e ADR-013)

Status: 🔄 Em andamento

---

## Sprint 5

IA e Ingestão

- Sugestão de patrocinadores via Lei Rouanet ✅ — integração SALIC completa: endpoint `/api/inteligencia/patrocinadores-salic` busca empresas incentivadoras por estado via `api.salic.cultura.gov.br`, com deduplicação automática e confiança de 85%, criando `OportunidadeCandidata` na fila de revisão
- Pipeline de candidatas ✅ — domínio `OportunidadeCandidata` completo: staging com proveniência rastreada, score de confiança, fluxo de promoção de organização (com estado obrigatório), aprovação e rejeição; formalizado em ADR-012
- Sugestão de abordagem — ⏳ não iniciado
- Recomendações automáticas — 🔄 parcial, mesma base do item de Recomendações da Sprint 4

Status: 🔄 Em andamento

---

## Sprint 6

Frontend

*Esta sprint não estava prevista no roadmap original. O desenvolvimento do frontend iniciou-se de forma orgânica durante as sessões de produto e arquitetura, e é aqui formalizado.*

**Stack:** Vite + React + TypeScript, Tailwind CSS v4, React Router v6, d3-geo + world-atlas TopoJSON, Nominatim para geocodificação.
**Deploy:** Render (site estático), auto-deploy via GitHub.

### Telas concluídas

- **Home — Mapa do ecossistema** ✅ — mapa vetorial do Brasil com clustering geográfico, rotas tracejadas com espessura proporcional à prioridade do Relacionamento, badge terracota de conexões internas em clusters, legenda interativa
- **Perfil de Organização** ✅ — pipeline de relacionamento, documentos, oportunidades, contatos, seção "Patrocinadores reais" via SALIC
- **Perfil de Projeto** ✅ — dados do projeto, pipeline de patrocinadores reais, seção de oportunidades vinculadas
- **Cadastro de Organização** ✅ — formulário com geocodificação automática via Nominatim/OpenStreetMap
- **Cadastro de Projeto** ✅ — formulário com geocodificação automática
- **Cadastro de Oportunidade Candidata** ✅
- **Revisão de Candidatas** ✅ — seleção de tipo e estado obrigatório antes de promover, fluxo completo de aprovação e rejeição

### Telas pendentes

- **Perfil de Relacionamento** — ⏳ não iniciado — **próximo passo prioritário**: tela central que expõe o interior de uma negociação (contatos vinculados, histórico de interações, documentos, oportunidades e tarefas)
- **Formulário de Contato** — ⏳ aguarda Perfil de Relacionamento
- **Formulário de Interação** — ⏳ aguarda Perfil de Relacionamento
- **Formulário de Tarefa** — ⏳ aguarda Perfil de Relacionamento
- **Dashboard** — ⏳ não iniciado
- **Tela de Recomendações** (conexões sugeridas) — ⏳ não iniciado

Status: 🔄 Em andamento

---

## Observações

- O backend de todos os **onze domínios** (Organização, Projeto, Relacionamento, Contato, Interação, Documento, Oportunidade, Tarefa, OportunidadeCandidata, Interesse + junções, Inteligência) está completo e testado de ponta a ponta em produção.
- A maior lacuna atual é o **Perfil de Relacionamento** no frontend — o centro do domínio existe no banco e na API, mas ainda não tem tela própria. Contato, Interação e Tarefa estão implementados no backend e aguardam essa tela para se tornarem visíveis ao usuário.
- A entrega seguiu uma ordem diferente da sequência de sprints original, guiada pelas necessidades de produto em cada sessão.
- O banco de dados está na migration **V20** (ON DELETE CASCADE em toda a cadeia de FKs), garantindo integridade referencial em exclusões em cascata.