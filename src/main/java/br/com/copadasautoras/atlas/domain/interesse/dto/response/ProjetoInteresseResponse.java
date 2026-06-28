package br.com.copadasautoras.atlas.domain.interesse.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjetoInteresseResponse(

        UUID id,

        UUID projetoId,

        String projetoNome,

        UUID interesseId,

        String interesseNome,

        Short peso,

        Short criticidade,

        String observacoes,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}