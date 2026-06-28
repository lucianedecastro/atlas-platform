package br.com.copadasautoras.atlas.domain.oportunidade.dto.request;

import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CriarOportunidadeRequest(

        @NotNull(message = "O relacionamento é obrigatório.")
        UUID relacionamentoId,

        @NotBlank(message = "O título da oportunidade é obrigatório.")
        String titulo,

        @NotNull(message = "O tipo da oportunidade é obrigatório.")
        TipoOportunidade tipo,

        BigDecimal valorSolicitado,

        @Min(value = 0, message = "A probabilidade não pode ser menor que 0.")
        @Max(value = 100, message = "A probabilidade não pode ser maior que 100.")
        Integer probabilidade,

        LocalDate dataPrevista,

        String descricao,

        String observacoes

) {
}
