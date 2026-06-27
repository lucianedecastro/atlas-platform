# ADR-004 — Mermaid como padrão oficial para diagramas

## Status

Aceita

## Data

2026-06-27

---

# Contexto

O Atlas possui extensa documentação arquitetural composta por DER, diagramas de sequência, componentes e fluxos.

Era necessário definir um padrão versionável e integrado ao Git.

---

# Decisão

Todos os diagramas oficiais serão produzidos utilizando Mermaid.

---

# Motivos

- Arquivos em texto.
- Versionamento via Git.
- Fácil manutenção.
- Compatível com Markdown.
- Integração com documentação técnica.

---

# Consequências

## Positivas

- Diagramas evoluem junto com o código.
- Diferenças podem ser revisadas em Pull Requests.
- Elimina dependência de ferramentas proprietárias.

## Negativas

- Algumas representações possuem limitações visuais.
- Diagramas muito grandes exigem organização modular.

---

# Situação futura

Mermaid será utilizado para DER, diagramas de sequência, componentes e fluxos arquiteturais.