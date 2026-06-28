package br.com.copadasautoras.atlas.domain.interesse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarInteresseRequest(

        @NotBlank(message = "O nome do interesse é obrigatório.")
        String nome,

        String categoria,

        String descricao,

        @NotNull(message = "O campo ativo é obrigatório.")
        Boolean ativo

) {
}
