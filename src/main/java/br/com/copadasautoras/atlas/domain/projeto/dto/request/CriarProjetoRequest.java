package br.com.copadasautoras.atlas.domain.projeto.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CriarProjetoRequest(

        @NotBlank(message = "O nome do projeto é obrigatório.")
        String nome,

        String descricao,

        String categoria,

        String proponente,

        Boolean leiIncentivo,

        BigDecimal orcamentoPrevisto,

        LocalDate dataInicio,

        LocalDate dataFim,

        String cidade,

        String estado,

        BigDecimal latitude,

        BigDecimal longitude

) {
}