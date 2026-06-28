package br.com.copadasautoras.atlas.domain.relacionamento.dto.response;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.StatusRelacionamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record RelacionamentoResponse(

        UUID id,

        UUID projetoId,

        String projetoNome,

        UUID organizacaoId,

        String organizacaoNome,

        StatusRelacionamento status,

        String origem,

        String objetivo,

        PrioridadeRelacionamento prioridade,

        String responsavel,

        String observacoes,

        LocalDate dataInicio,

        LocalDate dataEncerramento,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}