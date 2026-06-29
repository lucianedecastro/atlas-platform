package br.com.copadasautoras.atlas.domain.tarefa.dto.request;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarTarefaRequest(

        @NotBlank(message = "O título da tarefa é obrigatório.")
        String titulo,

        String descricao,

        @NotNull(message = "O status da tarefa é obrigatório.")
        StatusTarefa status,

        PrioridadeRelacionamento prioridade,

        LocalDate prazo,

        String responsavel

) {
}