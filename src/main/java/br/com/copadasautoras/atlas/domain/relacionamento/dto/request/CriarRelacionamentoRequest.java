package br.com.copadasautoras.atlas.domain.relacionamento.dto.request;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.StatusRelacionamento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CriarRelacionamentoRequest(

        @NotNull(message = "O projeto é obrigatório.")
        UUID projetoId,

        @NotNull(message = "A organização é obrigatória.")
        UUID organizacaoId,

        @NotNull(message = "O status do relacionamento é obrigatório.")
        StatusRelacionamento status,

        String origem,

        String objetivo,

        PrioridadeRelacionamento prioridade,

        String responsavel,

        String observacoes,

        @NotNull(message = "A data de início é obrigatória.")
        LocalDate dataInicio

) {
}
