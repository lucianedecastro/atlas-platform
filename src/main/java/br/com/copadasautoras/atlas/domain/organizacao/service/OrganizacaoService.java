package br.com.copadasautoras.atlas.domain.organizacao.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.organizacao.dto.request.AtualizarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.request.CriarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.response.OrganizacaoResponse;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import br.com.copadasautoras.atlas.domain.organizacao.mapper.OrganizacaoMapper;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OrganizacaoService {

    private final OrganizacaoRepository repository;

    public OrganizacaoService(OrganizacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OrganizacaoResponse criar(CriarOrganizacaoRequest request) {

        if (request.cnpj() != null && repository.existsByCnpj(request.cnpj())) {
            throw new IllegalArgumentException("Já existe uma organização cadastrada com este CNPJ.");
        }

        Organizacao organizacao = OrganizacaoMapper.toEntity(request);

        return OrganizacaoMapper.toResponse(repository.saveAndFlush(organizacao));
    }

    public OrganizacaoResponse buscarPorId(UUID id) {
        return OrganizacaoMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<OrganizacaoResponse> listarTodas() {
        return repository.findAll().stream()
                .map(OrganizacaoMapper::toResponse)
                .toList();
    }

    public List<OrganizacaoResponse> listarPorTipo(TipoOrganizacao tipo) {
        return repository.findByTipo(tipo).stream()
                .map(OrganizacaoMapper::toResponse)
                .toList();
    }

    public List<OrganizacaoResponse> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(OrganizacaoMapper::toResponse)
                .toList();
    }

    public List<OrganizacaoResponse> listarPorEstado(String estado) {
        return repository.findByEstado(estado).stream()
                .map(OrganizacaoMapper::toResponse)
                .toList();
    }

    public List<OrganizacaoResponse> listarPorEstadoETipo(String estado, TipoOrganizacao tipo) {
        return repository.findByEstadoAndTipo(estado, tipo).stream()
                .map(OrganizacaoMapper::toResponse)
                .toList();
    }

    @Transactional
    public OrganizacaoResponse atualizar(UUID id, AtualizarOrganizacaoRequest request) {

        Organizacao organizacao = buscarEntidadePorId(id);
        OrganizacaoMapper.atualizar(organizacao, request);

        return OrganizacaoMapper.toResponse(repository.saveAndFlush(organizacao));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Organizacao buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada."));
    }

}