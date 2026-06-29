package br.com.copadasautoras.atlas.domain.ingestao.service;

import br.com.copadasautoras.atlas.core.exception.RecursoNaoEncontradoException;
import br.com.copadasautoras.atlas.domain.ingestao.client.SalicClient;
import br.com.copadasautoras.atlas.domain.ingestao.dto.response.ResultadoBuscaPatrocinadoresResponse;
import br.com.copadasautoras.atlas.domain.ingestao.dto.salic.SalicIncentivadorDto;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.OportunidadeCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.repository.OportunidadeCandidataRepository;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.organizacao.repository.OrganizacaoRepository;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.projeto.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MotorBuscaPatrocinadoresService {

    private static final int LIMITE_RESULTADOS = 15;

    private final SalicClient salicClient;
    private final ProjetoRepository projetoRepository;
    private final OrganizacaoRepository organizacaoRepository;
    private final OportunidadeCandidataRepository candidataRepository;

    public MotorBuscaPatrocinadoresService(SalicClient salicClient,
                                           ProjetoRepository projetoRepository,
                                           OrganizacaoRepository organizacaoRepository,
                                           OportunidadeCandidataRepository candidataRepository) {
        this.salicClient = salicClient;
        this.projetoRepository = projetoRepository;
        this.organizacaoRepository = organizacaoRepository;
        this.candidataRepository = candidataRepository;
    }

    @Transactional
    public ResultadoBuscaPatrocinadoresResponse buscarParaProjeto(UUID projetoId) {

        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado."));

        if (projeto.getEstado() == null || projeto.getEstado().isBlank()) {
            throw new IllegalArgumentException(
                    "O projeto precisa ter um estado cadastrado para buscar patrocinadores reais."
            );
        }

        List<SalicIncentivadorDto> incentivadores =
                salicClient.buscarPorEstado(projeto.getEstado(), LIMITE_RESULTADOS);

        Set<String> cnpjsExistentes = organizacaoRepository.findAll().stream()
                .map(Organizacao::getCnpj)
                .filter(cnpj -> cnpj != null && !cnpj.isBlank())
                .map(this::somenteDigitos)
                .collect(Collectors.toSet());

        int jaCadastrados = 0;
        int jaSugeridos = 0;
        int novasCandidatas = 0;

        for (SalicIncentivadorDto incentivador : incentivadores) {

            String cgccpfNormalizado = somenteDigitos(incentivador.cgccpf());

            if (cnpjsExistentes.contains(cgccpfNormalizado)) {
                jaCadastrados++;
                continue;
            }

            boolean jaSugerido = candidataRepository.existsByProjetoIdAndOrganizacaoNomeSugerido(
                    projetoId, incentivador.nome()
            );

            if (jaSugerido) {
                jaSugeridos++;
                continue;
            }

            OportunidadeCandidata candidata = new OportunidadeCandidata();
            candidata.setTitulo("Patrocinador ativo na região — " + incentivador.nome());
            candidata.setTipo(TipoOportunidade.PATROCINIO);
            candidata.setDescricao(
                    "Identificado via dados públicos da Lei Rouanet (SALIC). Total já doado registrado: R$ "
                            + incentivador.totalDoado() + ", com atuação em " + incentivador.municipio()
                            + "/" + incentivador.uf() + "."
            );
            candidata.setFonte("SALIC — Incentivadores Lei Rouanet");
            candidata.setUrlOrigem(
                    "https://api.salic.cultura.gov.br/api/v1/incentivadores?cgccpf=" + incentivador.cgccpf()
            );
            candidata.setConfianca(85);
            candidata.setStatus(StatusCandidata.PENDENTE);
            candidata.setProjeto(projeto);
            candidata.setOrganizacaoNomeSugerido(incentivador.nome());

            candidataRepository.saveAndFlush(candidata);
            novasCandidatas++;
        }

        return new ResultadoBuscaPatrocinadoresResponse(
                incentivadores.size(), novasCandidatas, jaCadastrados, jaSugeridos
        );
    }

    private String somenteDigitos(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replaceAll("\\D", "");
    }

}