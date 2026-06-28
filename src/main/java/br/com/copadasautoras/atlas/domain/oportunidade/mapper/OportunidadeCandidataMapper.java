package br.com.copadasautoras.atlas.domain.oportunidade.mapper;

import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeCandidataRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeCandidataResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.OportunidadeCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;

import java.time.LocalDate;

public final class OportunidadeCandidataMapper {

    private OportunidadeCandidataMapper() {
    }

    public static OportunidadeCandidata toEntity(CriarOportunidadeCandidataRequest request,
                                                 Projeto projeto,
                                                 Organizacao organizacao) {

        OportunidadeCandidata candidata = new OportunidadeCandidata();
        candidata.setTitulo(request.titulo());
        candidata.setTipo(request.tipo());
        candidata.setDescricao(request.descricao());
        candidata.setValorEstimado(request.valorEstimado());
        candidata.setDataLimite(request.dataLimite());
        candidata.setFonte(request.fonte());
        candidata.setUrlOrigem(request.urlOrigem());
        candidata.setConfianca(request.confianca());
        candidata.setProjeto(projeto);
        candidata.setOrganizacao(organizacao);
        candidata.setOrganizacaoNomeSugerido(request.organizacaoNomeSugerido());
        candidata.setObservacoes(request.observacoes());

        return candidata;
    }

    public static void atualizar(OportunidadeCandidata candidata,
                                 AtualizarOportunidadeCandidataRequest request,
                                 Projeto projeto,
                                 Organizacao organizacao) {

        candidata.setTitulo(request.titulo());
        candidata.setTipo(request.tipo());
        candidata.setDescricao(request.descricao());
        candidata.setValorEstimado(request.valorEstimado());
        candidata.setDataLimite(request.dataLimite());
        candidata.setFonte(request.fonte());
        candidata.setUrlOrigem(request.urlOrigem());
        candidata.setConfianca(request.confianca());
        candidata.setProjeto(projeto);
        candidata.setOrganizacao(organizacao);
        candidata.setOrganizacaoNomeSugerido(request.organizacaoNomeSugerido());
        candidata.setObservacoes(request.observacoes());
    }

    public static OportunidadeCandidataResponse toResponse(OportunidadeCandidata candidata) {

        Projeto projeto = candidata.getProjeto();
        Organizacao organizacao = candidata.getOrganizacao();

        return new OportunidadeCandidataResponse(
                candidata.getId(),
                candidata.getTitulo(),
                candidata.getTipo(),
                candidata.getDescricao(),
                candidata.getValorEstimado(),
                candidata.getDataLimite(),
                candidata.getFonte(),
                candidata.getUrlOrigem(),
                candidata.getConfianca(),
                candidata.getStatus(),
                calcularExpirada(candidata),
                projeto != null ? projeto.getId() : null,
                projeto != null ? projeto.getNome() : null,
                organizacao != null ? organizacao.getId() : null,
                organizacao != null ? organizacao.getNome() : null,
                candidata.getOrganizacaoNomeSugerido(),
                candidata.getObservacoes(),
                candidata.getCreatedAt(),
                candidata.getUpdatedAt()
        );
    }

    private static boolean calcularExpirada(OportunidadeCandidata candidata) {

        return candidata.getStatus() == StatusCandidata.PENDENTE
                && candidata.getDataLimite() != null
                && candidata.getDataLimite().isBefore(LocalDate.now());
    }

}
