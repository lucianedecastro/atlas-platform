package br.com.copadasautoras.atlas.domain.oportunidade.dto.response;

import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record OportunidadeResponse(

        UUID id,

        UUID relacionamentoId,

        UUID projetoId,

        String projetoNome,

        UUID organizacaoId,

        String organizacaoNome,

        String titulo,

        TipoOportunidade tipo,

        StatusOportunidade status,

        BigDecimal valorSolicitado,

        BigDecimal valorAprovado,

        Integer probabilidade,

        LocalDate dataPrevista,

        LocalDate dataFechamento,

        String descricao,

        String observacoes,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
