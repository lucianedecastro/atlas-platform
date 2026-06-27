# DER — Atlas

## Versão

0.1.0

## Objetivo

Este documento descreve o Modelo Entidade-Relacionamento (MER/DER) oficial do Atlas.

O objetivo é representar as entidades de domínio, seus atributos principais e os relacionamentos utilizados pelo banco de dados da aplicação.

As migrations Flyway devem sempre refletir este documento.

---

# Diagrama

```mermaid

erDiagram

    PROJETO {

        UUID id PK
        string nome
        string categoria
        string status
    }

    ORGANIZACAO {

        UUID id PK
        string nome
        string tipo
        string status_relacionamento
    }

    RELACIONAMENTO {

        UUID id PK
        UUID projeto_id FK
        UUID organizacao_id FK
        string status
        string prioridade
    }

    CONTATO {

        UUID id PK
        UUID relacionamento_id FK
        string nome
        string email
    }

    INTERACAO {

        UUID id PK
        UUID relacionamento_id FK
        string tipo
        datetime data_interacao
    }

    OPORTUNIDADE {

        UUID id PK
        UUID relacionamento_id FK
        string tipo
        string status
    }

    TAREFA {

        UUID id PK
        UUID oportunidade_id FK
        string titulo
        string status
    }

    DOCUMENTO {

        UUID id PK
        UUID relacionamento_id FK
        string titulo
        string tipo
    }

    INTERESSE {

        UUID id PK
        string nome
    }

    ORGANIZACAO_INTERESSE {

        UUID id PK
        UUID organizacao_id FK
        UUID interesse_id FK
        smallint peso
        smallint confianca
    }

    PROJETO_INTERESSE {

        UUID id PK
        UUID projeto_id FK
        UUID interesse_id FK
        smallint peso
        smallint criticidade
    }

    PROJETO ||--o{ RELACIONAMENTO : possui

    ORGANIZACAO ||--o{ RELACIONAMENTO : participa

    RELACIONAMENTO ||--o{ CONTATO : possui

    RELACIONAMENTO ||--o{ INTERACAO : registra

    RELACIONAMENTO ||--o{ DOCUMENTO : possui

    RELACIONAMENTO ||--o{ OPORTUNIDADE : gera

    OPORTUNIDADE ||--o{ TAREFA : possui

    PROJETO ||--o{ PROJETO_INTERESSE : define

    INTERESSE ||--o{ PROJETO_INTERESSE : classifica

    ORGANIZACAO ||--o{ ORGANIZACAO_INTERESSE : possui

    INTERESSE ||--o{ ORGANIZACAO_INTERESSE : caracteriza

```

---

## Agregados

O domínio do Atlas está organizado em quatro agregados principais:

### Projeto

- Projeto
- ProjetoInteresse

### Organização

- Organização
- OrganizaçãoInteresse

### Relacionamento

- Relacionamento
- Contato
- Interação
- Documento
- Oportunidade
- Tarefa

### Inteligência

- Interesse
- Score de Afinidade (futuro)
- Indicadores (futuro)

---

## Observações

Este DER representa a arquitetura lógica do Atlas na versão 0.1.0.

Novas entidades deverão ser incorporadas mediante novas migrations Flyway e atualização deste documento.