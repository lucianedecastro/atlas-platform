package br.com.copadasautoras.atlas.domain.ingestao.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.ingestao.dto.response.ResultadoBuscaPatrocinadoresResponse;
import br.com.copadasautoras.atlas.domain.ingestao.service.MotorBuscaPatrocinadoresService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/ingestao/salic")
public class IngestaoSalicController {

    private final MotorBuscaPatrocinadoresService service;

    public IngestaoSalicController(MotorBuscaPatrocinadoresService service) {
        this.service = service;
    }

    @PostMapping("/projetos/{projetoId}/buscar-patrocinadores")
    public ApiResponse<ResultadoBuscaPatrocinadoresResponse> buscarPatrocinadores(
            @PathVariable UUID projetoId) {

        return ApiResponse.success("Busca realizada com sucesso.", service.buscarParaProjeto(projetoId));
    }

}