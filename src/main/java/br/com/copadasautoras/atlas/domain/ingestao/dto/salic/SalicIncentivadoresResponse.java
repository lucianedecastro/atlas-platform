package br.com.copadasautoras.atlas.domain.ingestao.dto.salic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SalicIncentivadoresResponse(

        @JsonProperty("_embedded")
        Embedded embedded,

        Integer total

) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Embedded(
            List<SalicIncentivadorDto> incentivadores
    ) {
    }

}
