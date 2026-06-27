# ARQUITETURA

## Stack

- Java 21
- Spring Boot 3.5
- PostgreSQL 17
- Flyway
- Docker
- Spring Data JPA
- Lombok
- Validation
- OpenAPI

---

## Arquitetura

Controller

↓

Service

↓

Repository

↓

PostgreSQL

---

## Versionamento do banco

Todo ajuste estrutural deverá ser realizado exclusivamente através de migrations Flyway.

O Hibernate não cria nem altera estruturas do banco.

```
spring.jpa.hibernate.ddl-auto=validate
```

---

## Identificadores

Todas as entidades utilizam UUID.

---

## Banco

PostgreSQL executando em Docker.

---

## API

REST

JSON

UTF-8

---

## Documentação

Swagger / OpenAPI

---

## Organização dos pacotes

controller

service

repository

entity

dto

mapper

config

exception

validation

audit

util

enums