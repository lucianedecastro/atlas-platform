package br.com.copadasautoras.atlas.domain.contato.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarContatoRequest(

        @NotBlank(message = "O nome do contato é obrigatório.")
        String nome,

        String cargo,

        String email,

        String telefone,

        String linkedin,

        Boolean principal,

        String observacoes,

        @NotNull(message = "O campo ativo é obrigatório.")
        Boolean ativo

) {
}
