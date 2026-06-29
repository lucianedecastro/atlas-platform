package br.com.copadasautoras.atlas.domain.interacao.dto.request;

import br.com.copadasautoras.atlas.domain.interacao.enums.ResultadoInteracao;
import br.com.copadasautoras.atlas.domain.interacao.enums.TipoInteracao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtualizarInteracaoRequest(

        @NotNull(message = "O tipo da interação é obrigatório.")
        TipoInteracao tipo,

        ResultadoInteracao resultado,

        @NotBlank(message = "O título da interação é obrigatório.")
        String titulo,

        String descricao,

        @NotNull(message = "A data da interação é obrigatória.")
        LocalDateTime dataInteracao,

        String proximaAcao,

        LocalDate dataProximaAcao,

        String realizadaPor

) {
}
