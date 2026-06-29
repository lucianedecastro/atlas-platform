package br.com.copadasautoras.atlas.domain.tarefa.dto.response;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaResponse(

        UUID id,

        UUID oportunidadeId,

        String oportunidadeTitulo,

        String projetoNome,

        String organizacaoNome,

        String titulo,

        String descricao,

        StatusTarefa status,

        PrioridadeRelacionamento prioridade,

        LocalDate prazo,

        LocalDateTime concluidaEm,

        String responsavel,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}