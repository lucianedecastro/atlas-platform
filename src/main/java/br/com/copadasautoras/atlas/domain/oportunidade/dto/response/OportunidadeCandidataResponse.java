package br.com.copadasautoras.atlas.domain.oportunidade.dto.response;

import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record OportunidadeCandidataResponse(

        UUID id,

        String titulo,

        TipoOportunidade tipo,

        String descricao,

        BigDecimal valorEstimado,

        LocalDate dataLimite,

        String fonte,

        String urlOrigem,

        Integer confianca,

        StatusCandidata status,

        Boolean expirada,

        UUID projetoId,

        String projetoNome,

        UUID organizacaoId,

        String organizacaoNome,

        String organizacaoNomeSugerido,

        String observacoes,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
