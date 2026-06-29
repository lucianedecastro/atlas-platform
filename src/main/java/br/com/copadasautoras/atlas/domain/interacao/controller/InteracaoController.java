package br.com.copadasautoras.atlas.domain.interacao.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.interacao.dto.request.AtualizarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.request.CriarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.response.InteracaoResponse;
import br.com.copadasautoras.atlas.domain.interacao.service.InteracaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interacoes")
public class InteracaoController {

    private final InteracaoService service;

    public InteracaoController(InteracaoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<InteracaoResponse>> listar(@RequestParam UUID relacionamentoId) {
        return ApiResponse.success(service.listarPorRelacionamento(relacionamentoId));
    }

    @GetMapping("/{id}")
    public ApiResponse<InteracaoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<InteracaoResponse> criar(@RequestBody @Valid CriarInteracaoRequest request) {
        return ApiResponse.success("Interação registrada com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<InteracaoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarInteracaoRequest request) {

        return ApiResponse.success("Interação atualizada com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}