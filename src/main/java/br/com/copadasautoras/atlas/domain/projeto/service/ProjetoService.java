package br.com.copadasautoras.atlas.domain.projeto.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.projeto.dto.request.AtualizarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.request.CriarProjetoRequest;
import br.com.copadasautoras.atlas.domain.projeto.dto.response.ProjetoResponse;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import br.com.copadasautoras.atlas.domain.projeto.mapper.ProjetoMapper;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProjetoService {

    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ProjetoResponse criar(CriarProjetoRequest request) {
        Projeto projeto = ProjetoMapper.toEntity(request);
        return ProjetoMapper.toResponse(repository.saveAndFlush(projeto));
    }

    public ProjetoResponse buscarPorId(UUID id) {
        return ProjetoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<ProjetoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(ProjetoMapper::toResponse)
                .toList();
    }

    public List<ProjetoResponse> listarPorStatus(StatusProjeto status) {
        return repository.findByStatus(status).stream()
                .map(ProjetoMapper::toResponse)
                .toList();
    }

    public List<ProjetoResponse> listarPorEstado(String estado) {
        return repository.findByEstado(estado).stream()
                .map(ProjetoMapper::toResponse)
                .toList();
    }

    public List<ProjetoResponse> listarPorEstadoEStatus(String estado, StatusProjeto status) {
        return repository.findByEstadoAndStatus(estado, status).stream()
                .map(ProjetoMapper::toResponse)
                .toList();
    }

    @Transactional
    public ProjetoResponse atualizar(UUID id, AtualizarProjetoRequest request) {
        Projeto projeto = buscarEntidadePorId(id);
        ProjetoMapper.atualizar(projeto, request);
        return ProjetoMapper.toResponse(repository.saveAndFlush(projeto));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Projeto buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));
    }

}