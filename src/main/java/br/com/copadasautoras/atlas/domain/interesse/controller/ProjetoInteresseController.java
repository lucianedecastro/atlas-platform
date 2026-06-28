package br.com.copadasautoras.atlas.domain.interesse.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.ProjetoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.service.ProjetoInteresseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projeto-interesses")
public class ProjetoInteresseController {

    private final ProjetoInteresseService service;

    public ProjetoInteresseController(ProjetoInteresseService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ProjetoInteresseResponse>> listar(
            @RequestParam(required = false) UUID projetoId,
            @RequestParam(required = false) UUID interesseId) {

        if (projetoId != null) {
            return ApiResponse.success(service.listarPorProjeto(projetoId));
        }

        if (interesseId != null) {
            return ApiResponse.success(service.listarPorInteresse(interesseId));
        }

        throw new IllegalArgumentException("Informe projetoId ou interesseId para listar.");
    }

    @GetMapping("/{id}")
    public ApiResponse<ProjetoInteresseResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProjetoInteresseResponse> criar(@RequestBody @Valid CriarProjetoInteresseRequest request) {
        return ApiResponse.success("Vínculo de interesse criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProjetoInteresseResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarProjetoInteresseRequest request) {

        return ApiResponse.success("Vínculo de interesse atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
