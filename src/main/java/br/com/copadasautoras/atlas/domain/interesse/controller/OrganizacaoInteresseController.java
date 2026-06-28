package br.com.copadasautoras.atlas.domain.interesse.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.OrganizacaoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.service.OrganizacaoInteresseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizacao-interesses")
public class OrganizacaoInteresseController {

    private final OrganizacaoInteresseService service;

    public OrganizacaoInteresseController(OrganizacaoInteresseService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<OrganizacaoInteresseResponse>> listar(
            @RequestParam(required = false) UUID organizacaoId,
            @RequestParam(required = false) UUID interesseId) {

        if (organizacaoId != null) {
            return ApiResponse.success(service.listarPorOrganizacao(organizacaoId));
        }

        if (interesseId != null) {
            return ApiResponse.success(service.listarPorInteresse(interesseId));
        }

        throw new IllegalArgumentException("Informe organizacaoId ou interesseId para listar.");
    }

    @GetMapping("/{id}")
    public ApiResponse<OrganizacaoInteresseResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrganizacaoInteresseResponse> criar(
            @RequestBody @Valid CriarOrganizacaoInteresseRequest request) {

        return ApiResponse.success("Vínculo de interesse criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrganizacaoInteresseResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarOrganizacaoInteresseRequest request) {

        return ApiResponse.success("Vínculo de interesse atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
