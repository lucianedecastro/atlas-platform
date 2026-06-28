package br.com.copadasautoras.atlas.domain.inteligencia.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.inteligencia.dto.response.LacunaGeograficaResponse;
import br.com.copadasautoras.atlas.domain.inteligencia.dto.response.ScoreAfinidadeResponse;
import br.com.copadasautoras.atlas.domain.inteligencia.service.InteligenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inteligencia")
public class InteligenciaController {

    private final InteligenciaService service;

    public InteligenciaController(InteligenciaService service) {
        this.service = service;
    }

    @GetMapping("/conexoes-sugeridas")
    public ApiResponse<List<ScoreAfinidadeResponse>> conexoesSugeridas(
            @RequestParam(required = false) UUID projetoId,
            @RequestParam(required = false) UUID organizacaoId) {

        return ApiResponse.success(service.listarConexoesSugeridas(projetoId, organizacaoId));
    }

    @GetMapping("/lacunas-geograficas")
    public ApiResponse<List<LacunaGeograficaResponse>> lacunasGeograficas() {
        return ApiResponse.success(service.listarLacunasGeograficas());
    }

}