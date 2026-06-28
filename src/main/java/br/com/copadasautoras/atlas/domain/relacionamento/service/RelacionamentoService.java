package br.com.copadasautoras.atlas.domain.relacionamento.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.AtualizarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.CriarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.response.RelacionamentoResponse;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.mapper.RelacionamentoMapper;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class RelacionamentoService {

    private final RelacionamentoRepository repository;
    private final OrganizacaoRepository organizacaoRepository;
    private final ProjetoRepository projetoRepository;

    public RelacionamentoService(RelacionamentoRepository repository,
                                 OrganizacaoRepository organizacaoRepository,
                                 ProjetoRepository projetoRepository) {
        this.repository = repository;
        this.organizacaoRepository = organizacaoRepository;
        this.projetoRepository = projetoRepository;
    }

    @Transactional
    public RelacionamentoResponse criar(CriarRelacionamentoRequest request) {

        Projeto projeto = projetoRepository.findById(request.projetoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));

        Organizacao organizacao = organizacaoRepository.findById(request.organizacaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada."));

        Relacionamento relacionamento = RelacionamentoMapper.toEntity(request, projeto, organizacao);

        return RelacionamentoMapper.toResponse(repository.saveAndFlush(relacionamento));
    }

    public RelacionamentoResponse buscarPorId(UUID id) {
        return RelacionamentoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<RelacionamentoResponse> listarPorOrganizacao(UUID organizacaoId) {
        return repository.findByOrganizacaoId(organizacaoId).stream()
                .map(RelacionamentoMapper::toResponse)
                .toList();
    }

    public List<RelacionamentoResponse> listarPorProjeto(UUID projetoId) {
        return repository.findByProjetoId(projetoId).stream()
                .map(RelacionamentoMapper::toResponse)
                .toList();
    }

    @Transactional
    public RelacionamentoResponse atualizar(UUID id, AtualizarRelacionamentoRequest request) {

        Relacionamento relacionamento = buscarEntidadePorId(id);
        RelacionamentoMapper.atualizar(relacionamento, request);

        return RelacionamentoMapper.toResponse(repository.saveAndFlush(relacionamento));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Relacionamento buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relacionamento não encontrado."));
    }

}