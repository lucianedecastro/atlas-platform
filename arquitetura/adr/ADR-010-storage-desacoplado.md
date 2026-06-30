# ADR-010 — Camada de Storage desacoplada do banco

## Status

Aceita — implementação pendente

## Data

2026-06-29

---

# Contexto

O domínio `Documento` prevê o armazenamento de arquivos físicos (apresentações, contratos, propostas, materiais institucionais) vinculados a relacionamentos, projetos e organizações.

Armazenar arquivos diretamente no banco de dados causaria crescimento descontrolado, degradação de performance e dificuldades de backup.

---

# Decisão

Arquivos físicos não serão armazenados no banco de dados.

O banco armazenará exclusivamente metadados: título, tipo, URL de acesso, proveniência e vínculos com Relacionamento, Projeto ou Organização.

A camada de armazenamento físico será um serviço externo desacoplado da aplicação.

---

# Motivos

- Banco de dados focado em dados relacionais, não binários.
- Facilidade de backup e replicação de arquivos independentemente do banco.
- Escalabilidade horizontal do armazenamento.
- Compatibilidade com serviços de nuvem (S3, GCS, R2).

---

# Consequências

## Positivas

- Banco leve e rápido.
- Storage independente e escalável.
- URLs de acesso podem ser assinadas com expiração.

## Negativas

- Requer integração com serviço externo de storage.
- Consistência entre banco e storage deve ser gerenciada pela aplicação.

---

# Situação futura

A implementação do upload físico utilizará um serviço compatível com S3 (Cloudflare R2 ou AWS S3). Enquanto não implementado, o campo `urlArquivo` em `Documento` aceita URLs externas inseridas manualmente.