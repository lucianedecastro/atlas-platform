package br.com.copadasautoras.atlas.domain.organizacao.dto.response;

import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrganizacaoResponse(

        UUID id,

        String nome,

        String nomeFantasia,

        String cnpj,

        TipoOrganizacao tipo,

        String segmento,

        String site,

        String descricao,

        Boolean utilizaLeiIncentivo,

        Boolean possuiInstituto,

        String observacoes,

        String cidade,

        String estado,

        BigDecimal latitude,

        BigDecimal longitude,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}