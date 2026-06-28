package br.com.copadasautoras.atlas.domain.interesse.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.OrganizacaoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import br.com.copadasautoras.atlas.domain.interesse.entity.OrganizacaoInteresse;
import br.com.copadasautoras.atlas.domain.interesse.mapper.OrganizacaoInteresseMapper;
import br.com.copadasautoras.atlas.domain.interesse.repository.InteresseRepository;
import br.com.copadasautoras.atlas.domain.interesse.repository.OrganizacaoInteresseRepository;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OrganizacaoInteresseService {

    private final OrganizacaoInteresseRepository repository;
    private final OrganizacaoRepository organizacaoRepository;
    private final InteresseRepository interesseRepository;

    public OrganizacaoInteresseService(OrganizacaoInteresseRepository repository,
                                       OrganizacaoRepository organizacaoRepository,
                                       InteresseRepository interesseRepository) {
        this.repository = repository;
        this.organizacaoRepository = organizacaoRepository;
        this.interesseRepository = interesseRepository;
    }

    @Transactional
    public OrganizacaoInteresseResponse criar(CriarOrganizacaoInteresseRequest request) {

        if (repository.existsByOrganizacaoIdAndInteresseId(request.organizacaoId(), request.interesseId())) {
            throw new IllegalArgumentException("Esta organização já está vinculada a este interesse.");
        }

        Organizacao organizacao = organizacaoRepository.findById(request.organizacaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada."));

        Interesse interesse = interesseRepository.findById(request.interesseId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Interesse não encontrado."));

        OrganizacaoInteresse organizacaoInteresse =
                OrganizacaoInteresseMapper.toEntity(request, organizacao, interesse);

        return OrganizacaoInteresseMapper.toResponse(repository.saveAndFlush(organizacaoInteresse));
    }

    public OrganizacaoInteresseResponse buscarPorId(UUID id) {
        return OrganizacaoInteresseMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<OrganizacaoInteresseResponse> listarPorOrganizacao(UUID organizacaoId) {
        return repository.findByOrganizacaoId(organizacaoId).stream()
                .map(OrganizacaoInteresseMapper::toResponse)
                .toList();
    }

    public List<OrganizacaoInteresseResponse> listarPorInteresse(UUID interesseId) {
        return repository.findByInteresseId(interesseId).stream()
                .map(OrganizacaoInteresseMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrganizacaoInteresseResponse atualizar(UUID id, AtualizarOrganizacaoInteresseRequest request) {

        OrganizacaoInteresse organizacaoInteresse = buscarEntidadePorId(id);
        OrganizacaoInteresseMapper.atualizar(organizacaoInteresse, request);

        return OrganizacaoInteresseMapper.toResponse(repository.saveAndFlush(organizacaoInteresse));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private OrganizacaoInteresse buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Vínculo de interesse não encontrado."));
    }

}
