package br.com.copadasautoras.atlas.domain.interesse.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.InteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import br.com.copadasautoras.atlas.domain.interesse.mapper.InteresseMapper;
import br.com.copadasautoras.atlas.domain.interesse.repository.InteresseRepository;
import br.com.copadasautoras.atlas.domain.interesse.repository.OrganizacaoInteresseRepository;
import br.com.copadasautoras.atlas.domain.interesse.repository.ProjetoInteresseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class InteresseService {

    private final InteresseRepository repository;
    private final OrganizacaoInteresseRepository organizacaoInteresseRepository;
    private final ProjetoInteresseRepository projetoInteresseRepository;

    public InteresseService(InteresseRepository repository,
                            OrganizacaoInteresseRepository organizacaoInteresseRepository,
                            ProjetoInteresseRepository projetoInteresseRepository) {
        this.repository = repository;
        this.organizacaoInteresseRepository = organizacaoInteresseRepository;
        this.projetoInteresseRepository = projetoInteresseRepository;
    }

    @Transactional
    public InteresseResponse criar(CriarInteresseRequest request) {

        if (repository.existsByNomeIgnoreCase(request.nome())) {
            throw new IllegalArgumentException("Já existe um interesse cadastrado com este nome.");
        }

        Interesse interesse = InteresseMapper.toEntity(request);

        return InteresseMapper.toResponse(repository.saveAndFlush(interesse));
    }

    public InteresseResponse buscarPorId(UUID id) {
        return InteresseMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<InteresseResponse> listarTodos() {
        return repository.findAll().stream()
                .map(InteresseMapper::toResponse)
                .toList();
    }

    public List<InteresseResponse> listarPorAtivo(Boolean ativo) {
        return repository.findByAtivo(ativo).stream()
                .map(InteresseMapper::toResponse)
                .toList();
    }

    public List<InteresseResponse> listarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream()
                .map(InteresseMapper::toResponse)
                .toList();
    }

    @Transactional
    public InteresseResponse atualizar(UUID id, AtualizarInteresseRequest request) {

        Interesse interesse = buscarEntidadePorId(id);
        InteresseMapper.atualizar(interesse, request);

        return InteresseMapper.toResponse(repository.saveAndFlush(interesse));
    }

    @Transactional
    public void excluir(UUID id) {

        Interesse interesse = buscarEntidadePorId(id);

        boolean emUso = organizacaoInteresseRepository.existsByInteresseId(id)
                || projetoInteresseRepository.existsByInteresseId(id);

        if (emUso) {
            throw new IllegalArgumentException(
                    "Este interesse já está vinculado a organizações ou projetos. Desative-o em vez de excluir."
            );
        }

        repository.delete(interesse);
    }

    private Interesse buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Interesse não encontrado."));
    }

}
