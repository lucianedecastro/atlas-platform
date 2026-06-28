package br.com.copadasautoras.atlas.domain.interesse.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.InteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.service.InteresseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interesses")
public class InteresseController {

    private final InteresseService service;

    public InteresseController(InteresseService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<InteresseResponse>> listar(
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(required = false) String categoria) {

        if (ativo != null) {
            return ApiResponse.success(service.listarPorAtivo(ativo));
        }

        if (categoria != null) {
            return ApiResponse.success(service.listarPorCategoria(categoria));
        }

        return ApiResponse.success(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ApiResponse<InteresseResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<InteresseResponse> criar(@RequestBody @Valid CriarInteresseRequest request) {
        return ApiResponse.success("Interesse criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<InteresseResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarInteresseRequest request) {

        return ApiResponse.success("Interesse atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
