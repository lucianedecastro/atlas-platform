# ADR-009 — Docker como ambiente padrão de desenvolvimento

## Status

Aceita

## Data

2026-06-29

---

# Contexto

O Atlas é desenvolvido com Java 21 e Spring Boot, dependendo de um servidor PostgreSQL disponível localmente durante o desenvolvimento. Era necessário garantir que qualquer desenvolvedor pudesse subir o ambiente sem configurações manuais de banco.

---

# Decisão

O ambiente de desenvolvimento do Atlas utiliza Docker e Docker Compose para provisionar o PostgreSQL localmente.

---

# Motivos

- Elimina dependência de instalação manual do PostgreSQL.
- Garante paridade entre ambientes de desenvolvimento.
- Facilita onboarding de novos colaboradores.
- Isola o banco de desenvolvimento do sistema operacional.

---

# Consequências

## Positivas

- Ambiente reproduzível e versionável.
- Startup do banco em um comando.
- Compatível com CI/CD futuramente.

## Negativas

- Requer Docker instalado na máquina.
- Overhead de memória em relação a banco nativo.

---

# Observação operacional

O workflow atual da Copa das Autoras mantém Docker e IntelliJ fechados durante o desenvolvimento, testando diretamente contra o banco de produção (Neon PostgreSQL). O Docker permanece como padrão recomendado para novos colaboradores e ambientes isolados.

---

# Situação futura

Docker continuará sendo o padrão de desenvolvimento local. A infraestrutura de produção utiliza Neon PostgreSQL (serverless) e Render, independentemente do Docker.