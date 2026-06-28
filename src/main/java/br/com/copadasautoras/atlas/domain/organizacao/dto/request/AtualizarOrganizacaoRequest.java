package br.com.copadasautoras.atlas.domain.organizacao.dto.request;

import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizarOrganizacaoRequest(

        @NotBlank(message = "O nome da organização é obrigatório.")
        String nome,

        String nomeFantasia,

        String cnpj,

        @NotNull(message = "O tipo da organização é obrigatório.")
        TipoOrganizacao tipo,

        String segmento,

        String site,

        String descricao,

        Boolean utilizaLeiIncentivo,

        Boolean possuiInstituto,

        String observacoes,

        String cidade,

        String estado,

        BigDecimal latitude,

        BigDecimal longitude

) {
}