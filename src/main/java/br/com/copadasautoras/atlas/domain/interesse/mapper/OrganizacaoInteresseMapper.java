package br.com.copadasautoras.atlas.domain.interesse.mapper;

import br.com.copadasautoras.atlas.domain.interesse.dto.request.AtualizarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.request.CriarOrganizacaoInteresseRequest;
import br.com.copadasautoras.atlas.domain.interesse.dto.response.OrganizacaoInteresseResponse;
import br.com.copadasautoras.atlas.domain.interesse.entity.Interesse;
import br.com.copadasautoras.atlas.domain.interesse.entity.OrganizacaoInteresse;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;

public final class OrganizacaoInteresseMapper {

    private OrganizacaoInteresseMapper() {
    }

    public static OrganizacaoInteresse toEntity(CriarOrganizacaoInteresseRequest request,
                                                Organizacao organizacao,
                                                Interesse interesse) {

        OrganizacaoInteresse organizacaoInteresse = new OrganizacaoInteresse();
        organizacaoInteresse.setOrganizacao(organizacao);
        organizacaoInteresse.setInteresse(interesse);
        organizacaoInteresse.setPeso(request.peso());
        organizacaoInteresse.setConfianca(request.confianca() != null ? request.confianca() : (short) 100);
        organizacaoInteresse.setFonte(request.fonte());
        organizacaoInteresse.setObservacoes(request.observacoes());

        return organizacaoInteresse;
    }

    public static void atualizar(OrganizacaoInteresse organizacaoInteresse,
                                 AtualizarOrganizacaoInteresseRequest request) {

        organizacaoInteresse.setPeso(request.peso());

        if (request.confianca() != null) {
            organizacaoInteresse.setConfianca(request.confianca());
        }

        organizacaoInteresse.setFonte(request.fonte());
        organizacaoInteresse.setObservacoes(request.observacoes());
    }

    public static OrganizacaoInteresseResponse toResponse(OrganizacaoInteresse organizacaoInteresse) {

        Organizacao organizacao = organizacaoInteresse.getOrganizacao();
        Interesse interesse = organizacaoInteresse.getInteresse();

        return new OrganizacaoInteresseResponse(
                organizacaoInteresse.getId(),
                organizacao.getId(),
                organizacao.getNome(),
                interesse.getId(),
                interesse.getNome(),
                organizacaoInteresse.getPeso(),
                organizacaoInteresse.getConfianca(),
                organizacaoInteresse.getFonte(),
                organizacaoInteresse.getObservacoes(),
                organizacaoInteresse.getCreatedAt(),
                organizacaoInteresse.getUpdatedAt()
        );
    }

}
