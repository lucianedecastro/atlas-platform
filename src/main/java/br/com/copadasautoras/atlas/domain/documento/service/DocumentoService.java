package br.com.copadasautoras.atlas.domain.documento.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.documento.dto.request.AtualizarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.request.CriarDocumentoRequest;
import br.com.copadasautoras.atlas.domain.documento.dto.response.DocumentoResponse;
import br.com.copadasautoras.atlas.domain.documento.entity.Documento;
import br.com.copadasautoras.atlas.domain.documento.mapper.DocumentoMapper;
import br.com.copadasautoras.atlas.domain.documento.repository.DocumentoRepository;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class DocumentoService {

    private final DocumentoRepository repository;
    private final RelacionamentoRepository relacionamentoRepository;
    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;

    public DocumentoService(DocumentoRepository repository,
                            RelacionamentoRepository relacionamentoRepository,
                            ProjetoRepository projetoRepository,
                            OrganizacaoRepository organizacaoRepository) {
        this.repository = repository;
        this.relacionamentoRepository = relacionamentoRepository;
        this.projetoRepository = projetoRepository;
        this.organizacaoRepository = organizacaoRepository;
    }

    @Transactional
    public DocumentoResponse criar(CriarDocumentoRequest request) {

        validarContexto(request.relacionamentoId(), request.projetoId(), request.organizacaoId());

        Relacionamento relacionamento = request.relacionamentoId() != null
                ? relacionamentoRepository.findById(request.relacionamentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relacionamento não encontrado."))
                : null;

        Projeto projeto = request.projetoId() != null
                ? projetoRepository.findById(request.projetoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."))
                : null;

        Organizacao organizacao = request.organizacaoId() != null
                ? organizacaoRepository.findById(request.organizacaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada."))
                : null;

        Documento documento = DocumentoMapper.toEntity(request, relacionamento, projeto, organizacao);

        return DocumentoMapper.toResponse(repository.saveAndFlush(documento));
    }

    public DocumentoResponse buscarPorId(UUID id) {
        return DocumentoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<DocumentoResponse> listarPorRelacionamento(UUID relacionamentoId) {
        return repository.findByRelacionamentoId(relacionamentoId).stream()
                .map(DocumentoMapper::toResponse)
                .toList();
    }

    public List<DocumentoResponse> listarPorProjeto(UUID projetoId) {
        return repository.findByProjetoId(projetoId).stream()
                .map(DocumentoMapper::toResponse)
                .toList();
    }

    public List<DocumentoResponse> listarPorOrganizacao(UUID organizacaoId) {
        return repository.findByOrganizacaoId(organizacaoId).stream()
                .map(DocumentoMapper::toResponse)
                .toList();
    }

    @Transactional
    public DocumentoResponse atualizar(UUID id, AtualizarDocumentoRequest request) {
        Documento documento = buscarEntidadePorId(id);
        DocumentoMapper.atualizar(documento, request);
        return DocumentoMapper.toResponse(repository.saveAndFlush(documento));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Documento buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento não encontrado."));
    }

    private void validarContexto(UUID relacionamentoId, UUID projetoId, UUID organizacaoId) {

        boolean temRelacionamento = relacionamentoId != null;
        boolean temProjetoOuOrganizacao = projetoId != null || organizacaoId != null;

        if (temRelacionamento && temProjetoOuOrganizacao) {
            throw new IllegalArgumentException(
                    "Um documento de negociação não pode também apontar para projeto ou organização diretamente."
            );
        }

        if (!temRelacionamento && !temProjetoOuOrganizacao) {
            throw new IllegalArgumentException(
                    "Informe um relacionamento, ou um projeto e/ou organização, para contextualizar o documento."
            );
        }
    }

}