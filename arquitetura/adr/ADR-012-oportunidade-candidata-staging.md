# ADR-012 — OportunidadeCandidata como pipeline de staging com revisão humana

## Status

Aceita

## Data

2026-06-29 (implementado durante Sprint 5, formalizado aqui)

---

# Contexto

O Atlas possui um objetivo de longo prazo: alimentar automaticamente a fila de oportunidades com dados provenientes de fontes externas — editais públicos, sistemas de incentivo fiscal, patrocinadores identificados por IA.

O risco dessa automação é óbvio: dados não confiáveis ou mal interpretados entrando diretamente no banco limpo do Atlas contaminariam o conhecimento institucional que é o principal ativo da plataforma.

Era necessário definir como dados de origem externa entrariam no sistema sem comprometer a integridade dos dados confirmados.

---

# Decisão

Dados provenientes de fontes externas ou sugestões automáticas nunca entram diretamente no domínio principal do Atlas.

Toda sugestão de oportunidade, patrocinador ou parceiro passa obrigatoriamente pelo domínio `OportunidadeCandidata` — uma área de staging com revisão humana explícita antes de qualquer promoção.

---

# Estrutura do pipeline

```
Fonte externa (SALIC, IA, edital)
        ↓
OportunidadeCandidata (staging)
  - título
  - tipo
  - fonte (proveniência rastreada)
  - confiança (0–100, percentual explícito)
  - status: PENDENTE → APROVADA | REJEITADA | EXPIRADA
  - organizacaoNomeSugerido (nome antes da promoção)
  - organizacaoId (após promoção)
  - projetoId (vínculo com projeto)
        ↓
   Revisão humana obrigatória
        ↓
  Promover Organização      Rejeitar
  (cria Organizacao real    (arquiva com motivo)
   se não existir)
        ↓
      Aprovar
  (cria Relacionamento +
   Oportunidade real)
```

---

# Motivos

- Separa dados confirmados de sugestões automáticas.
- Proveniência rastreada: sempre se sabe de onde veio cada dado.
- Confiança explícita: o sistema nunca finge certeza que não tem.
- Revisão humana como etapa obrigatória: o Atlas amplifica o julgamento humano, não o substitui.
- Permite rejeição com motivo registrado, alimentando aprendizado futuro.

---

# Integração atual: SALIC API

A primeira fonte externa integrada é a API SALIC (`api.salic.cultura.gov.br`), que expõe dados públicos de incentivadores da Lei Rouanet.

O endpoint `/api/inteligencia/patrocinadores-salic` busca empresas que já patrocinaram projetos no mesmo estado do projeto solicitante, cria `OportunidadeCandidata` com:

- `fonte = "SALIC — Incentivadores Lei Rouanet"`
- `confianca = 85`
- deduplicação automática por nome antes de criar nova candidata

Todas as sugestões entram no pipeline de revisão, sem criação automática de Organização ou Relacionamento.

---

# Consequências

## Positivas

- Banco principal livre de dados não confirmados.
- Rastreabilidade completa da origem de cada organização sugerida.
- Confiança explícita permite priorização na revisão.
- Humano sempre no controle da decisão final.

## Negativas

- Automação total é intencionalmente impedida.
- Revisão humana cria gargalo proporcional ao volume de sugestões.
- Candidatas expiradas precisam de processo de limpeza periódica.

---

# Situação futura

Novas fontes externas (editais de cultura, Lei de Incentivo ao Esporte, APIs de OSC, sugestões de LLM) devem ser integradas exclusivamente via `OportunidadeCandidata`, nunca criando entidades principais diretamente.