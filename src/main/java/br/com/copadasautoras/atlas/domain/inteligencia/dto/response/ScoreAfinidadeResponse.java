package br.com.copadasautoras.atlas.domain.inteligencia.dto.response;

import java.util.UUID;

public record ScoreAfinidadeResponse(

        UUID projetoId,

        String projetoNome,

        UUID organizacaoId,

        String organizacaoNome,

        Long interessesEmComum,

        Long scoreBruto

) {
}