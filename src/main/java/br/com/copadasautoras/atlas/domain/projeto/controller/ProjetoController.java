package br.com.copadasautoras.atlas.domain.projeto.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.projeto.dto.request.AtualizarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.request.CriarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.response.ProjetoResponse;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import br.com.copadasautoras.atlas.domain.projeto.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ProjetoResponse>> listar(
            @RequestParam(required = false) StatusProjeto status,
            @RequestParam(required = false) String estado) {

        if (status != null && estado != null) {
            return ApiResponse.success(service.listarPorEstadoEStatus(estado, status));
        }

        if (status != null) {
            return ApiResponse.success(service.listarPorStatus(status));
        }

        if (estado != null) {
            return ApiResponse.success(service.listarPorEstado(estado));
        }

        return ApiResponse.success(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ApiResponse<ProjetoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProjetoResponse> criar(@RequestBody @Valid CriarProjetoRequest request) {
        return ApiResponse.success("Projeto criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProjetoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarProjetoRequest request) {

        return ApiResponse.success("Projeto atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}