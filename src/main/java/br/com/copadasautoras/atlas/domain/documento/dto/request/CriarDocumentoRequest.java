package br.com.copadasautoras.atlas.domain.documento.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record CriarDocumentoRequest(

        @NotBlank(message = "O título do documento é obrigatório.")
        String titulo,

        @NotBlank(message = "O tipo do documento é obrigatório.")
        String tipo,

        String descricao,

        @NotBlank(message = "O nome do arquivo é obrigatório.")
        String nomeArquivo,

        String caminhoArquivo,

        String urlArquivo,

        LocalDate dataDocumento,

        UUID relacionamentoId,

        UUID projetoId,

        UUID organizacaoId

) {
}
