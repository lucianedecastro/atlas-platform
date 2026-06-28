package br.com.copadasautoras.atlas.domain.oportunidade.mapper;

import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.AtualizarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.request.CriarOportunidadeRequest;
import br.com.copadasautoras.atlas.domain.oportunidade.dto.response.OportunidadeResponse;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;

public final class OportunidadeMapper {

    private OportunidadeMapper() {
    }

    public static Oportunidade toEntity(CriarOportunidadeRequest request, Relacionamento relacionamento) {

        Oportunidade oportunidade = new Oportunidade();
        oportunidade.setRelacionamento(relacionamento);
        oportunidade.setTitulo(request.titulo());
        oportunidade.setTipo(request.tipo());
        oportunidade.setValorSolicitado(request.valorSolicitado());
        oportunidade.setProbabilidade(request.probabilidade() != null ? request.probabilidade() : 0);
        oportunidade.setDataPrevista(request.dataPrevista());
        oportunidade.setDescricao(request.descricao());
        oportunidade.setObservacoes(request.observacoes());

        return oportunidade;
    }

    public static void atualizar(Oportunidade oportunidade, AtualizarOportunidadeRequest request) {

        if (request.titulo() != null) {
            oportunidade.setTitulo(request.titulo());
        }

        if (request.tipo() != null) {
            oportunidade.setTipo(request.tipo());
        }

        oportunidade.setStatus(request.status());
        oportunidade.setValorSolicitado(request.valorSolicitado());
        oportunidade.setValorAprovado(request.valorAprovado());

        if (request.probabilidade() != null) {
            oportunidade.setProbabilidade(request.probabilidade());
        }

        oportunidade.setDataPrevista(request.dataPrevista());
        oportunidade.setDataFechamento(request.dataFechamento());
        oportunidade.setDescricao(request.descricao());
        oportunidade.setObservacoes(request.observacoes());
    }

    public static OportunidadeResponse toResponse(Oportunidade oportunidade) {

        Relacionamento relacionamento = oportunidade.getRelacionamento();
        Projeto projeto = relacionamento.getProjeto();
        Organizacao organizacao = relacionamento.getOrganizacao();

        return new OportunidadeResponse(
                oportunidade.getId(),
                relacionamento.getId(),
                projeto.getId(),
                projeto.getNome(),
                organizacao.getId(),
                organizacao.getNome(),
                oportunidade.getTitulo(),
                oportunidade.getTipo(),
                oportunidade.getStatus(),
                oportunidade.getValorSolicitado(),
                oportunidade.getValorAprovado(),
                oportunidade.getProbabilidade(),
                oportunidade.getDataPrevista(),
                oportunidade.getDataFechamento(),
                oportunidade.getDescricao(),
                oportunidade.getObservacoes(),
                oportunidade.getCreatedAt(),
                oportunidade.getUpdatedAt()
        );
    }

}
