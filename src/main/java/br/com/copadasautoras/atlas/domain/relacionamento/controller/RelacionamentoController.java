package br.com.copadasautoras.atlas.domain.relacionamento.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.AtualizarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.CriarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.response.RelacionamentoResponse;
import br.com.copadasautoras.atlas.domain.relacionamento.service.RelacionamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/relacionamentos")
public class RelacionamentoController {

    private final RelacionamentoService service;

    public RelacionamentoController(RelacionamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<RelacionamentoResponse>> listar(
            @RequestParam(required = false) UUID organizacaoId,
            @RequestParam(required = false) UUID projetoId) {

        if (organizacaoId != null) {
            return ApiResponse.success(service.listarPorOrganizacao(organizacaoId));
        }

        if (projetoId != null) {
            return ApiResponse.success(service.listarPorProjeto(projetoId));
        }

        throw new IllegalArgumentException("Informe organizacaoId ou projetoId para listar relacionamentos.");
    }

    @GetMapping("/{id}")
    public ApiResponse<RelacionamentoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<RelacionamentoResponse> criar(@RequestBody @Valid CriarRelacionamentoRequest request) {
        return ApiResponse.success("Relacionamento criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<RelacionamentoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarRelacionamentoRequest request) {

        return ApiResponse.success("Relacionamento atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
