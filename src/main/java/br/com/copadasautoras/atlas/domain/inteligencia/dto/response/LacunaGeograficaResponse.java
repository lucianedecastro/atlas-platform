package br.com.copadasautoras.atlas.domain.inteligencia.dto.response;

public record LacunaGeograficaResponse(

        String estado,

        Long projetosBuscandoPatrocinio,

        Long organizacoesPatrocinadoras,

        Long lacuna

) {
}