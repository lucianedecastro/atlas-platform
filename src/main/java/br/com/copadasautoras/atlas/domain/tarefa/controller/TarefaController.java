package br.com.copadasautoras.atlas.domain.tarefa.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.AtualizarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.CriarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.response.TarefaResponse;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;
import br.com.copadasautoras.atlas.domain.tarefa.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<TarefaResponse>> listar(
            @RequestParam(required = false) UUID oportunidadeId,
            @RequestParam(required = false) StatusTarefa status) {

        if (oportunidadeId != null) {
            return ApiResponse.success(service.listarPorOportunidade(oportunidadeId));
        }

        if (status != null) {
            return ApiResponse.success(service.listarPorStatus(status));
        }

        throw new IllegalArgumentException("Informe oportunidadeId ou status para listar tarefas.");
    }

    @GetMapping("/{id}")
    public ApiResponse<TarefaResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TarefaResponse> criar(@RequestBody @Valid CriarTarefaRequest request) {
        return ApiResponse.success("Tarefa criada com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<TarefaResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarTarefaRequest request) {

        return ApiResponse.success("Tarefa atualizada com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}