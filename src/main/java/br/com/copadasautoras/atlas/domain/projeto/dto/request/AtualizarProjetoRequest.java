package br.com.copadasautoras.atlas.domain.projeto.dto.request;

import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarProjetoRequest(

        @NotNull(message = "O status do projeto é obrigatório.")
        StatusProjeto status,

        String descricao,

        String categoria,

        String proponente,

        Boolean leiIncentivo,

        BigDecimal orcamentoPrevisto,

        BigDecimal valorCaptado,

        LocalDate dataInicio,

        LocalDate dataFim,

        String cidade,

        String estado,

        BigDecimal latitude,

        BigDecimal longitude

) {
}
