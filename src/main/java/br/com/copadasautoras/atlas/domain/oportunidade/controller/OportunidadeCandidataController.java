package br.com.copadasautoras.atlas.domain.oportunidade.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.RejeitarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeCandidataResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.service.OportunidadeCandidataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/oportunidades-candidatas")
public class OportunidadeCandidataController {

    private final OportunidadeCandidataService service;

    public OportunidadeCandidataController(OportunidadeCandidataService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<OportunidadeCandidataResponse>> listar(
            @RequestParam(required = false, defaultValue = "PENDENTE") StatusCandidata status) {

        return ApiResponse.success(service.listarPorStatus(status));
    }

    @GetMapping("/{id}")
    public ApiResponse<OportunidadeCandidataResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OportunidadeCandidataResponse> criar(
            @RequestBody @Valid CriarOportunidadeCandidataRequest request) {

        return ApiResponse.success("Oportunidade candidata registrada com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<OportunidadeCandidataResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarOportunidadeCandidataRequest request) {

        return ApiResponse.success("Oportunidade candidata atualizada com sucesso.", service.atualizar(id, request));
    }

    @PostMapping("/{id}/aprovar")
    public ApiResponse<OportunidadeResponse> aprovar(@PathVariable UUID id) {
        return ApiResponse.success("Candidata aprovada e promovida a oportunidade real.", service.aprovar(id));
    }

    @PostMapping("/{id}/rejeitar")
    public ApiResponse<OportunidadeCandidataResponse> rejeitar(
            @PathVariable UUID id,
            @RequestBody(required = false) RejeitarOportunidadeCandidataRequest request) {

        return ApiResponse.success("Candidata rejeitada.", service.rejeitar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
