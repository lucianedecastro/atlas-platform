package br.com.copadasautoras.atlas.domain.ingestao.dto.salic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SalicIncentivadorDto(

        String nome,

        String municipio,

        @JsonProperty("UF")
        String uf,

        String cgccpf,

        @JsonProperty("tipo_pessoa")
        String tipoPessoa,

        @JsonProperty("total_doado")
        BigDecimal totalDoado

) {
}
