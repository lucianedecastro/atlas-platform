package br.com.copadasautoras.atlas.domain.organizacao.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.organizacao.dto.request.AtualizarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.request.CriarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.response.OrganizacaoResponse;
import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import br.com.copadasautoras.atlas.domain.organizacao.service.OrganizacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizacoes")
public class OrganizacaoController {

    private final OrganizacaoService service;

    public OrganizacaoController(OrganizacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<OrganizacaoResponse>> listar(
            @RequestParam(required = false) TipoOrganizacao tipo,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String estado) {

        if (estado != null && tipo != null) {
            return ApiResponse.success(service.listarPorEstadoETipo(estado, tipo));
        }

        if (estado != null) {
            return ApiResponse.success(service.listarPorEstado(estado));
        }

        if (tipo != null) {
            return ApiResponse.success(service.listarPorTipo(tipo));
        }

        if (nome != null) {
            return ApiResponse.success(service.buscarPorNome(nome));
        }

        return ApiResponse.success(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ApiResponse<OrganizacaoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrganizacaoResponse> criar(@RequestBody @Valid CriarOrganizacaoRequest request) {
        return ApiResponse.success("Organização criada com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrganizacaoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarOrganizacaoRequest request) {

        return ApiResponse.success("Organização atualizada com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}