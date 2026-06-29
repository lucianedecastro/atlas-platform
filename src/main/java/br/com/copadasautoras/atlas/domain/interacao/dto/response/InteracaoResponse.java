package br.com.copadasautoras.atlas.domain.interacao.dto.response;

import br.com.copadasautoras.atlas.domain.interacao.enums.ResultadoInteracao;
import br.com.copadasautoras.atlas.domain.interacao.enums.TipoInteracao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record InteracaoResponse(

        UUID id,

        UUID relacionamentoId,

        String projetoNome,

        String organizacaoNome,

        TipoInteracao tipo,

        ResultadoInteracao resultado,

        String titulo,

        String descricao,

        LocalDateTime dataInteracao,

        String proximaAcao,

        LocalDate dataProximaAcao,

        String realizadaPor,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
