package br.com.copadasautoras.atlas.domain.interesse.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarProjetoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.ProjetoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import br.com.copadasautoras.atlas.domain.interesse.entity.ProjetoInteresse;
import br.com.copadasautoras.atlas.domain.interesse.mapper.ProjetoInteresseMapper;
import br.com.copadasautoras.atlas.domain.interesse.repository.InteresseRepository;
import br.com.copadasautoras.atlas.domain.interesse.repository.ProjetoInteresseRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProjetoInteresseService {

    private final ProjetoInteresseRepository repository;
    private final ProjetoRepository projetoRepository;
    private final InteresseRepository interesseRepository;

    public ProjetoInteresseService(ProjetoInteresseRepository repository,
                                   ProjetoRepository projetoRepository,
                                   InteresseRepository interesseRepository) {
        this.repository = repository;
        this.projetoRepository = projetoRepository;
        this.interesseRepository = interesseRepository;
    }

    @Transactional
    public ProjetoInteresseResponse criar(CriarProjetoInteresseRequest request) {

        if (repository.existsByProjetoIdAndInteresseId(request.projetoId(), request.interesseId())) {
            throw new IllegalArgumentException("Este projeto já está vinculado a este interesse.");
        }

        Projeto projeto = projetoRepository.findById(request.projetoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));

        Interesse interesse = interesseRepository.findById(request.interesseId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Interesse não encontrado."));

        ProjetoInteresse projetoInteresse = ProjetoInteresseMapper.toEntity(request, projeto, interesse);

        return ProjetoInteresseMapper.toResponse(repository.saveAndFlush(projetoInteresse));
    }

    public ProjetoInteresseResponse buscarPorId(UUID id) {
        return ProjetoInteresseMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<ProjetoInteresseResponse> listarPorProjeto(UUID projetoId) {
        return repository.findByProjetoId(projetoId).stream()
                .map(ProjetoInteresseMapper::toResponse)
                .toList();
    }

    public List<ProjetoInteresseResponse> listarPorInteresse(UUID interesseId) {
        return repository.findByInteresseId(interesseId).stream()
                .map(ProjetoInteresseMapper::toResponse)
                .toList();
    }

    @Transactional
    public ProjetoInteresseResponse atualizar(UUID id, AtualizarProjetoInteresseRequest request) {

        ProjetoInteresse projetoInteresse = buscarEntidadePorId(id);
        ProjetoInteresseMapper.atualizar(projetoInteresse, request);

        return ProjetoInteresseMapper.toResponse(repository.saveAndFlush(projetoInteresse));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private ProjetoInteresse buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Vínculo de interesse não encontrado."));
    }

}
