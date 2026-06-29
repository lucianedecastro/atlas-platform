package br.com.copadasautoras.atlas.domain.contato.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.contato.dto.request.AtualizarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.request.CriarContatoRequest;
import br.com.copadasautoras.atlas.domain.contato.dto.response.ContatoResponse;
import br.com.copadasautoras.atlas.domain.contato.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ContatoResponse>> listar(
            @RequestParam UUID relacionamentoId,
            @RequestParam(required = false) Boolean somenteAtivos) {

        if (Boolean.TRUE.equals(somenteAtivos)) {
            return ApiResponse.success(service.listarAtivosPorRelacionamento(relacionamentoId));
        }

        return ApiResponse.success(service.listarPorRelacionamento(relacionamentoId));
    }

    @GetMapping("/{id}")
    public ApiResponse<ContatoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ContatoResponse> criar(@RequestBody @Valid CriarContatoRequest request) {
        return ApiResponse.success("Contato criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ContatoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarContatoRequest request) {

        return ApiResponse.success("Contato atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
