package br.com.copadasautoras.atlas.domain.tarefa.dto.request;

import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CriarTarefaRequest(

        @NotNull(message = "A oportunidade é obrigatória.")
        UUID oportunidadeId,

        @NotBlank(message = "O título da tarefa é obrigatório.")
        String titulo,

        String descricao,

        PrioridadeRelacionamento prioridade,

        LocalDate prazo,

        String responsavel

) {
}
