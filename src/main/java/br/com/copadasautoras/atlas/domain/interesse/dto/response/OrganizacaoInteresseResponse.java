package br.com.copadasautoras.atlas.domain.interesse.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrganizacaoInteresseResponse(

        UUID id,

        UUID organizacaoId,

        String organizacaoNome,

        UUID interesseId,

        String interesseNome,

        Short peso,

        Short confianca,

        String fonte,

        String observacoes,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
