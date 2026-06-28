package br.com.copadasautoras.atlas.domain.documento.dto.response;

import br.com.copadasautoras.atlas.domain.documento.enums.ContextoDocumento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentoResponse(

        UUID id,

        String titulo,

        String tipo,

        String descricao,

        String nomeArquivo,

        String caminhoArquivo,

        String urlArquivo,

        Integer versao,

        LocalDate dataDocumento,

        ContextoDocumento contexto,

        UUID relacionamentoId,

        UUID projetoId,

        String projetoNome,

        UUID organizacaoId,

        String organizacaoNome,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}