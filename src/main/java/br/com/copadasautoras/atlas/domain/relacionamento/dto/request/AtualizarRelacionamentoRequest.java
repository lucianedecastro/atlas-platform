package br.com.copadasautoras.atlas.domain.relacionamento.dto.request;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.StatusRelacionamento;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarRelacionamentoRequest(

        @NotNull(message = "O status do relacionamento é obrigatório.")
        StatusRelacionamento status,

        String origem,

        String objetivo,

        PrioridadeRelacionamento prioridade,

        String responsavel,

        String observacoes,

        LocalDate dataEncerramento

) {
}
