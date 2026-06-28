package br.com.copadasautoras.atlas.domain.oportunidade.dto.request;

import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizarOportunidadeRequest(

        String titulo,

        TipoOportunidade tipo,

        @NotNull(message = "O status da oportunidade é obrigatório.")
        StatusOportunidade status,

        BigDecimal valorSolicitado,

        BigDecimal valorAprovado,

        @Min(value = 0, message = "A probabilidade não pode ser menor que 0.")
        @Max(value = 100, message = "A probabilidade não pode ser maior que 100.")
        Integer probabilidade,

        LocalDate dataPrevista,

        LocalDate dataFechamento,

        String descricao,

        String observacoes

) {
}