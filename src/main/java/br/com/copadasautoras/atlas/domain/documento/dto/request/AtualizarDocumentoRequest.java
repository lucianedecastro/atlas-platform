package br.com.copadasautoras.atlas.domain.documento.dto.request;

import java.time.LocalDate;

public record AtualizarDocumentoRequest(

        String titulo,

        String tipo,

        String descricao,

        String nomeArquivo,

        String caminhoArquivo,

        String urlArquivo,

        Integer versao,

        LocalDate dataDocumento

) {
}