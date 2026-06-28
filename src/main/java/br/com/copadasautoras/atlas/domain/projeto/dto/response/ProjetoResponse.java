package br.com.copadasautoras.atlas.domain.projeto.dto.response;

import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProjetoResponse(

        UUID id,

        String nome,

        String descricao,

        String categoria,

        StatusProjeto status,

        String proponente,

        Boolean leiIncentivo,

        BigDecimal orcamentoPrevisto,

        BigDecimal valorCaptado,

        LocalDate dataInicio,

        LocalDate dataFim,

        String cidade,

        String estado,

        BigDecimal latitude,

        BigDecimal longitude,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
