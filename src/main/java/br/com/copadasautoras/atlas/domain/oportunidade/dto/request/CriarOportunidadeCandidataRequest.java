package br.com.copadasautoras.atlas.domain.oportunidade.dto.request;

import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CriarOportunidadeCandidataRequest(

        @NotBlank(message = "O título da oportunidade candidata é obrigatório.")
        String titulo,

        @NotNull(message = "O tipo da oportunidade é obrigatório.")
        TipoOportunidade tipo,

        String descricao,

        BigDecimal valorEstimado,

        LocalDate dataLimite,

        @NotBlank(message = "A fonte de origem é obrigatória.")
        String fonte,

        String urlOrigem,

        @NotNull(message = "O grau de confiança é obrigatório.")
        @Min(value = 0, message = "A confiança não pode ser menor que 0.")
        @Max(value = 100, message = "A confiança não pode ser maior que 100.")
        Integer confianca,

        UUID projetoId,

        UUID organizacaoId,

        String organizacaoNomeSugerido,

        String observacoes

) {
}
