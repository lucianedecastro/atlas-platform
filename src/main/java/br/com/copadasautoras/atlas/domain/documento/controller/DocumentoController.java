package br.com.copadasautoras.atlas.domain.documento.controller;

import br.com.copadasautoras.atlas.core.response.ApiResponse;
import br.com.copadasautoras.atlas.domain.documento.dto.request.AtualizarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.request.CriarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.response.DocumentoResponse;
import br.com.copadasautoras.atlas.domain.documento.service.DocumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService service;

    public DocumentoController(DocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<DocumentoResponse>> listar(
            @RequestParam(required = false) UUID relacionamentoId,
            @RequestParam(required = false) UUID projetoId,
            @RequestParam(required = false) UUID organizacaoId) {

        if (relacionamentoId != null) {
            return ApiResponse.success(service.listarPorRelacionamento(relacionamentoId));
        }

        if (projetoId != null) {
            return ApiResponse.success(service.listarPorProjeto(projetoId));
        }

        if (organizacaoId != null) {
            return ApiResponse.success(service.listarPorOrganizacao(organizacaoId));
        }

        throw new IllegalArgumentException(
                "Informe relacionamentoId, projetoId ou organizacaoId para listar documentos."
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<DocumentoResponse> buscarPorId(@PathVariable UUID id) {
        return ApiResponse.success(service.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DocumentoResponse> criar(@RequestBody @Valid CriarDocumentoRequest request) {
        return ApiResponse.success("Documento criado com sucesso.", service.criar(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<DocumentoResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarDocumentoRequest request) {

        return ApiResponse.success("Documento atualizado com sucesso.", service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }

}
