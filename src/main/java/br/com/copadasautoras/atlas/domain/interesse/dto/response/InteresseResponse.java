package br.com.copadasautoras.atlas.domain.interesse.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record InteresseResponse(

        UUID id,

        String nome,

        String categoria,

        String descricao,

        Boolean ativo,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
