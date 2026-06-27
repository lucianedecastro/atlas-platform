# DECISÕES DE ARQUITETURA

## ADR-001

### PostgreSQL

Foi adotado PostgreSQL como banco principal devido à robustez, aderência ao ecossistema Spring e facilidade de deploy.

---

## ADR-002

### Flyway

Todo versionamento do banco é realizado exclusivamente pelo Flyway.

---

## ADR-003

### UUID

Todas as entidades utilizam UUID como chave primária.

---

## ADR-004

### Docker

O ambiente de desenvolvimento utiliza Docker para garantir portabilidade e padronização.

---

## ADR-005

### Arquitetura em Camadas

Controller

↓

Service

↓

Repository

---

## ADR-006

### Organização do Domínio

O Atlas não é um CRM de vendas.

É uma plataforma de relacionamento institucional voltada à inteligência de captação.

O relacionamento é considerado o elemento central do domínio.