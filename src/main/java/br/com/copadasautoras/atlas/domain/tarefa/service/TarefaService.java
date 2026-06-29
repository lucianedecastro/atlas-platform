package br.com.copadasautoras.atlas.domain.tarefa.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.repository.OportunidadeRepository;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.AtualizarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.CriarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.response.TarefaResponse;
import br.com.copadasautoras.atlas.domain.tarefa.entity.Tarefa;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;
import br.com.copadasautoras.atlas.domain.tarefa.mapper.TarefaMapper;
import br.com.copadasautoras.atlas.domain.tarefa.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TarefaService {

    private final TarefaRepository repository;
    private final OportunidadeRepository oportunidadeRepository;

    public TarefaService(TarefaRepository repository, OportunidadeRepository oportunidadeRepository) {
        this.repository = repository;
        this.oportunidadeRepository = oportunidadeRepository;
    }

    @Transactional
    public TarefaResponse criar(CriarTarefaRequest request) {

        Oportunidade oportunidade = oportunidadeRepository.findById(request.oportunidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oportunidade não encontrada."));

        Tarefa tarefa = TarefaMapper.toEntity(request, oportunidade);

        return TarefaMapper.toResponse(repository.saveAndFlush(tarefa));
    }

    public TarefaResponse buscarPorId(UUID id) {
        return TarefaMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<TarefaResponse> listarPorOportunidade(UUID oportunidadeId) {
        return repository.findByOportunidadeId(oportunidadeId).stream()
                .map(TarefaMapper::toResponse)
                .toList();
    }

    public List<TarefaResponse> listarPorStatus(StatusTarefa status) {
        return repository.findByStatus(status).stream()
                .map(TarefaMapper::toResponse)
                .toList();
    }

    @Transactional
    public TarefaResponse atualizar(UUID id, AtualizarTarefaRequest request) {
        Tarefa tarefa = buscarEntidadePorId(id);
        TarefaMapper.atualizar(tarefa, request);
        return TarefaMapper.toResponse(repository.saveAndFlush(tarefa));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Tarefa buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada."));
    }

}