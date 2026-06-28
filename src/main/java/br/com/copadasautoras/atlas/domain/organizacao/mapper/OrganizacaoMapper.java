package br.com.copadasautoras.atlas.domain.organizacao.mapper;

import br.com.copadasautoras.atlas.domain.organizacao.dto.request.AtualizarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.request.CriarOrganizacaoRequest;
import br.com.copadasautoras.atlas.domain.organizacao.dto.response.OrganizacaoResponse;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;

public final class OrganizacaoMapper {

    private OrganizacaoMapper() {
    }

    public static Organizacao toEntity(CriarOrganizacaoRequest request) {

        Organizacao organizacao = new Organizacao();
        organizacao.setNome(request.nome());
        organizacao.setNomeFantasia(request.nomeFantasia());
        organizacao.setCnpj(request.cnpj());
        organizacao.setTipo(request.tipo());
        organizacao.setSegmento(request.segmento());
        organizacao.setSite(request.site());
        organizacao.setDescricao(request.descricao());
        organizacao.setUtilizaLeiIncentivo(
                request.utilizaLeiIncentivo() != null ? request.utilizaLeiIncentivo() : false
        );
        organizacao.setPossuiInstituto(
                request.possuiInstituto() != null ? request.possuiInstituto() : false
        );
        organizacao.setObservacoes(request.observacoes());
        organizacao.setCidade(request.cidade());
        organizacao.setEstado(request.estado());
        organizacao.setLatitude(request.latitude());
        organizacao.setLongitude(request.longitude());

        return organizacao;
    }

    public static void atualizar(Organizacao organizacao, AtualizarOrganizacaoRequest request) {

        organizacao.setNome(request.nome());
        organizacao.setNomeFantasia(request.nomeFantasia());
        organizacao.setCnpj(request.cnpj());
        organizacao.setTipo(request.tipo());
        organizacao.setSegmento(request.segmento());
        organizacao.setSite(request.site());
        organizacao.setDescricao(request.descricao());

        if (request.utilizaLeiIncentivo() != null) {
            organizacao.setUtilizaLeiIncentivo(request.utilizaLeiIncentivo());
        }

        if (request.possuiInstituto() != null) {
            organizacao.setPossuiInstituto(request.possuiInstituto());
        }

        organizacao.setObservacoes(request.observacoes());
        organizacao.setCidade(request.cidade());
        organizacao.setEstado(request.estado());
        organizacao.setLatitude(request.latitude());
        organizacao.setLongitude(request.longitude());
    }

    public static OrganizacaoResponse toResponse(Organizacao organizacao) {

        return new OrganizacaoResponse(
                organizacao.getId(),
                organizacao.getNome(),
                organizacao.getNomeFantasia(),
                organizacao.getCnpj(),
                organizacao.getTipo(),
                organizacao.getSegmento(),
                organizacao.getSite(),
                organizacao.getDescricao(),
                organizacao.getUtilizaLeiIncentivo(),
                organizacao.getPossuiInstituto(),
                organizacao.getObservacoes(),
                organizacao.getCidade(),
                organizacao.getEstado(),
                organizacao.getLatitude(),
                organizacao.getLongitude(),
                organizacao.getCreatedAt(),
                organizacao.getUpdatedAt()
        );
    }

}