package br.com.copadasautoras.atlas.domain.oportunidade.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.mapper.OportunidadeMapper;
import br.com.copadasautoras.atlas.domain.oportunidade.repository.OportunidadeRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OportunidadeService {

    private final OportunidadeRepository repository;
    private final RelacionamentoRepository relacionamentoRepository;

    public OportunidadeService(OportunidadeRepository repository,
                               RelacionamentoRepository relacionamentoRepository) {
        this.repository = repository;
        this.relacionamentoRepository = relacionamentoRepository;
    }

    @Transactional
    public OportunidadeResponse criar(CriarOportunidadeRequest request) {

        Relacionamento relacionamento = relacionamentoRepository.findById(request.relacionamentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relacionamento não encontrado."));

        Oportunidade oportunidade = OportunidadeMapper.toEntity(request, relacionamento);

        return OportunidadeMapper.toResponse(repository.saveAndFlush(oportunidade));
    }

    public OportunidadeResponse buscarPorId(UUID id) {
        return OportunidadeMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<OportunidadeResponse> listarPorRelacionamento(UUID relacionamentoId) {
        return repository.findByRelacionamentoId(relacionamentoId).stream()
                .map(OportunidadeMapper::toResponse)
                .toList();
    }

    public List<OportunidadeResponse> listarPorStatus(StatusOportunidade status) {
        return repository.findByStatus(status).stream()
                .map(OportunidadeMapper::toResponse)
                .toList();
    }

    @Transactional
    public OportunidadeResponse atualizar(UUID id, AtualizarOportunidadeRequest request) {
        Oportunidade oportunidade = buscarEntidadePorId(id);
        OportunidadeMapper.atualizar(oportunidade, request);
        return OportunidadeMapper.toResponse(repository.saveAndFlush(oportunidade));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Oportunidade buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oportunidade não encontrada."));
    }

}