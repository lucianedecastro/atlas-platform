package br.com.copadasautoras.atlas.domain.oportunidade.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeCandidataResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.OportunidadeCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.mapper.OportunidadeCandidataMapper;
import br.com.copadasautoras.atlas.domain.oportunidade.mapper.OportunidadeMapper;
import br.com.copadasautoras.atlas.domain.oportunidade.repository.OportunidadeCandidataRepository;
import br.com.copadasautoras.atlas.domain.oportunidade.repository.OportunidadeRepository;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.StatusRelacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.repository.RelacionamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OportunidadeCandidataService {

    private final OportunidadeCandidataRepository repository;
    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;
    private final RelacionamentoRepository relacionamentoRepository;
    private final OportunidadeRepository oportunidadeRepository;

    public OportunidadeCandidataService(OportunidadeCandidataRepository repository,
                                        ProjetoRepository projetoRepository,
                                        OrganizacaoRepository organizacaoRepository,
                                        RelacionamentoRepository relacionamentoRepository,
                                        OportunidadeRepository oportunidadeRepository) {
        this.repository = repository;
        this.projetoRepository = projetoRepository;
        this.organizacaoRepository = organizacaoRepository;
        this.relacionamentoRepository = relacionamentoRepository;
        this.oportunidadeRepository = oportunidadeRepository;
    }

    @Transactional
    public OportunidadeCandidataResponse criar(CriarOportunidadeCandidataRequest request) {

        Projeto projeto = resolverProjeto(request.projetoId());
        Organizacao organizacao = resolverOrganizacao(request.organizacaoId());

        OportunidadeCandidata candidata = OportunidadeCandidataMapper.toEntity(request, projeto, organizacao);

        return OportunidadeCandidataMapper.toResponse(repository.saveAndFlush(candidata));
    }

    public OportunidadeCandidataResponse buscarPorId(UUID id) {
        return OportunidadeCandidataMapper.toResponse(buscarEntidadePorId(id));
    }

    public List<OportunidadeCandidataResponse> listarPorStatus(StatusCandidata status) {
        return repository.findByStatus(status).stream()
                .map(OportunidadeCandidataMapper::toResponse)
                .toList();
    }

    @Transactional
    public OportunidadeCandidataResponse atualizar(UUID id, AtualizarOportunidadeCandidataRequest request) {

        OportunidadeCandidata candidata = buscarEntidadePorId(id);

        if (candidata.getStatus() != StatusCandidata.PENDENTE) {
            throw new IllegalArgumentException("Só é possível editar uma candidata pendente de revisão.");
        }

        Projeto projeto = resolverProjeto(request.projetoId());
        Organizacao organizacao = resolverOrganizacao(request.organizacaoId());

        OportunidadeCandidataMapper.atualizar(candidata, request, projeto, organizacao);

        return OportunidadeCandidataMapper.toResponse(repository.saveAndFlush(candidata));
    }

    @Transactional
    public OportunidadeCandidataResponse promoverParaOrganizacao(UUID id, TipoOrganizacao tipoPreferido) {

        OportunidadeCandidata candidata = buscarEntidadePorId(id);

        if (candidata.getStatus() != StatusCandidata.PENDENTE) {
            throw new IllegalArgumentException("Esta candidata já foi avaliada anteriormente.");
        }

        if (candidata.getOrganizacao() != null) {
            throw new IllegalArgumentException("Esta candidata já está vinculada a uma organização.");
        }

        String nomeSugerido = candidata.getOrganizacaoNomeSugerido();

        if (nomeSugerido == null || nomeSugerido.isBlank()) {
            throw new IllegalArgumentException(
                    "Esta candidata não tem nome de organização sugerido para promover."
            );
        }

        Organizacao organizacao = organizacaoRepository.findByNomeContainingIgnoreCase(nomeSugerido).stream()
                .filter(o -> o.getNome().equalsIgnoreCase(nomeSugerido))
                .findFirst()
                .orElseGet(() -> criarOrganizacaoAPartirDaSugestao(nomeSugerido, tipoPreferido));

        candidata.setOrganizacao(organizacao);

        return OportunidadeCandidataMapper.toResponse(repository.saveAndFlush(candidata));
    }

    @Transactional
    public OportunidadeResponse aprovar(UUID id) {

        OportunidadeCandidata candidata = buscarEntidadePorId(id);

        if (candidata.getStatus() != StatusCandidata.PENDENTE) {
            throw new IllegalArgumentException("Esta candidata já foi avaliada anteriormente.");
        }

        if (candidata.getProjeto() == null || candidata.getOrganizacao() == null) {
            throw new IllegalArgumentException(
                    "Vincule um projeto e uma organização existentes antes de aprovar esta candidata."
            );
        }

        Relacionamento relacionamento = relacionamentoRepository
                .findByOrganizacaoIdAndProjetoId(candidata.getOrganizacao().getId(), candidata.getProjeto().getId())
                .stream()
                .findFirst()
                .orElseGet(() -> criarRelacionamento(candidata));

        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setRelacionamento(relacionamento);
        oportunidade.setTitulo(candidata.getTitulo());
        oportunidade.setTipo(candidata.getTipo());
        oportunidade.setValorSolicitado(candidata.getValorEstimado());
        oportunidade.setProbabilidade(0);
        oportunidade.setDataPrevista(candidata.getDataLimite());
        oportunidade.setDescricao(candidata.getDescricao());
        oportunidade.setObservacoes(
                "Promovida automaticamente da candidata sugerida via " + candidata.getFonte() + "."
        );

        Oportunidade salva = oportunidadeRepository.saveAndFlush(oportunidade);

        candidata.setStatus(StatusCandidata.APROVADA);
        repository.saveAndFlush(candidata);

        return OportunidadeMapper.toResponse(salva);
    }

    @Transactional
    public OportunidadeCandidataResponse rejeitar(UUID id, br.com.copadasautoras.atlas.domain.oportunidade.dto.request.RejeitarOportunidadeCandidataRequest request) {

        OportunidadeCandidata candidata = buscarEntidadePorId(id);

        if (candidata.getStatus() != StatusCandidata.PENDENTE) {
            throw new IllegalArgumentException("Esta candidata já foi avaliada anteriormente.");
        }

        candidata.setStatus(StatusCandidata.REJEITADA);

        if (request != null && request.motivo() != null) {
            candidata.setObservacoes(request.motivo());
        }

        return OportunidadeCandidataMapper.toResponse(repository.saveAndFlush(candidata));
    }

    @Transactional
    public void excluir(UUID id) {
        repository.delete(buscarEntidadePorId(id));
    }

    private Organizacao criarOrganizacaoAPartirDaSugestao(String nome, TipoOrganizacao tipoPreferido) {

        Organizacao nova = new Organizacao();
        nova.setNome(nome);
        nova.setTipo(tipoPreferido != null ? tipoPreferido : TipoOrganizacao.PATROCINADOR_POTENCIAL);
        nova.setUtilizaLeiIncentivo(true);
        nova.setPossuiInstituto(false);

        return organizacaoRepository.saveAndFlush(nova);
    }

    private Relacionamento criarRelacionamento(OportunidadeCandidata candidata) {

        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setProjeto(candidata.getProjeto());
        relacionamento.setOrganizacao(candidata.getOrganizacao());
        relacionamento.setStatus(StatusRelacionamento.PROSPECT);
        relacionamento.setPrioridade(PrioridadeRelacionamento.MEDIA);
        relacionamento.setOrigem(candidata.getFonte());
        relacionamento.setObjetivo(candidata.getTitulo());
        relacionamento.setDataInicio(LocalDate.now());

        return relacionamentoRepository.saveAndFlush(relacionamento);
    }

    private Projeto resolverProjeto(UUID projetoId) {

        if (projetoId == null) {
            return null;
        }

        return projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));
    }

    private Organizacao resolverOrganizacao(UUID organizacaoId) {

        if (organizacaoId == null) {
            return null;
        }

        return organizacaoRepository.findById(organizacaoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Organização não encontrada."));
    }

    private OportunidadeCandidata buscarEntidadePorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oportunidade candidata não encontrada."));
    }

}