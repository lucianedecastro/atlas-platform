package br.com.copadasautoras.atlas.domain.interesse.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CriarInteresseRequest(

        @NotBlank(message = "O nome do interesse é obrigatório.")
        String nome,

        String categoria,

        String descricao

) {
}
