package br.com.copadasautoras.atlas.domain.interacao.mapper;

import br.com.copadasautoras.atlas.domain.interacao.dto.request.AtualizarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.request.CriarInteracaoRequest;
import br.com.copadasautoras.atlas.domain.interacao.dto.response.InteracaoResponse;
import br.com.copadasautoras.atlas.domain.interacao.entity.Interacao;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;

public final class InteracaoMapper {

    private InteracaoMapper() {
    }

    public static Interacao toEntity(CriarInteracaoRequest request, Relacionamento relacionamento) {

        Interacao interacao = new Interacao();
        interacao.setRelacionamento(relacionamento);
        interacao.setTipo(request.tipo());
        interacao.setResultado(request.resultado());
        interacao.setTitulo(request.titulo());
        interacao.setDescricao(request.descricao());
        interacao.setDataInteracao(request.dataInteracao());
        interacao.setProximaAcao(request.proximaAcao());
        interacao.setDataProximaAcao(request.dataProximaAcao());
        interacao.setRealizadaPor(request.realizadaPor());

        return interacao;
    }

    public static void atualizar(Interacao interacao, AtualizarInteracaoRequest request) {

        interacao.setTipo(request.tipo());
        interacao.setResultado(request.resultado());
        interacao.setTitulo(request.titulo());
        interacao.setDescricao(request.descricao());
        interacao.setDataInteracao(request.dataInteracao());
        interacao.setProximaAcao(request.proximaAcao());
        interacao.setDataProximaAcao(request.dataProximaAcao());
        interacao.setRealizadaPor(request.realizadaPor());
    }

    public static InteracaoResponse toResponse(Interacao interacao) {

        Relacionamento relacionamento = interacao.getRelacionamento();

        return new InteracaoResponse(
                interacao.getId(),
                relacionamento.getId(),
                relacionamento.getProjeto().getNome(),
                relacionamento.getOrganizacao().getNome(),
                interacao.getTipo(),
                interacao.getResultado(),
                interacao.getTitulo(),
                interacao.getDescricao(),
                interacao.getDataInteracao(),
                interacao.getProximaAcao(),
                interacao.getDataProximaAcao(),
                interacao.getRealizadaPor(),
                interacao.getCreatedAt(),
                interacao.getUpdatedAt()
        );
    }

}
