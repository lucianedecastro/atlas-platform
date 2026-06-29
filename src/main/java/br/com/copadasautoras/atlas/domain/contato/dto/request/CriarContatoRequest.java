package br.com.copadasautoras.atlas.domain.contato.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CriarContatoRequest(

        @NotNull(message = "O relacionamento é obrigatório.")
        UUID relacionamentoId,

        @NotBlank(message = "O nome do contato é obrigatório.")
        String nome,

        String cargo,

        String email,

        String telefone,

        String linkedin,

        Boolean principal,

        String observacoes

) {
}