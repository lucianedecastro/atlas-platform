package br.com.copadasautoras.atlas.domain.contato.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContatoResponse(

        UUID id,

        UUID relacionamentoId,

        String projetoNome,

        String organizacaoNome,

        String nome,

        String cargo,

        String email,

        String telefone,

        String linkedin,

        Boolean principal,

        String observacoes,

        Boolean ativo,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
