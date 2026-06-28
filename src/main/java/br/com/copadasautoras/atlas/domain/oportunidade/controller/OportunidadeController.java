package br.com.copadasautoras.atlas.domain.oportunidade.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/oportunidades")
public class OportunidadeController {

    private final OportunidadeService service;

    public OportunidadeController(OportunidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<OportunidadeResponse>> listar(
            @RequestParam(required = false) UUID relacionamentoId,
            @RequestParam(required = false) StatusOportunidade status) {

        if (relacionamentoId != null) {
            return ApiResponse.success(service.listarPorRelacionamento(relacionamentoId));
        }

        if (status != null) {
            return ApiResponse.success(service.listarPorStatus(status));
        }

        throw new IllegalArgumentException("Informe relacionamentoId ou status para listar oportunidades.");
    }

    @GetMapping("/{id}")
    public ApiResponse<OportunidadeResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OportunidadeResponse> criar(@RequestBody @Valid CriarOportunidadeRequest request) {
        return ApiResponse.success("Oportunidade criada com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<OportunidadeResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarOportunidadeRequest request) {

        return ApiResponse.success("Oportunidade atualizada com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}