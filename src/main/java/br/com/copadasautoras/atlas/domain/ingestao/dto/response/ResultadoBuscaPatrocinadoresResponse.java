package br.com.copadasautoras.atlas.domain.ingestao.dto.response;

public record ResultadoBuscaPatrocinadoresResponse(

        int encontrados,

        int novasCandidatas,

        int jaCadastrados,

        int jaSugeridos

) {
}