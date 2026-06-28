package br.com.copadasautoras.atlas.domain.interesse.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AtualizarProjetoInteresseRequest(

        @NotNull(message = "O peso é obrigatório.")
        @Min(value = 1, message = "O peso não pode ser menor que 1.")
        @Max(value = 10, message = "O peso não pode ser maior que 10.")
        Short peso,

        @Min(value = 1, message = "A criticidade não pode ser menor que 1.")
        @Max(value = 5, message = "A criticidade não pode ser maior que 5.")
        Short criticidade,

        String observacoes

) {
}
