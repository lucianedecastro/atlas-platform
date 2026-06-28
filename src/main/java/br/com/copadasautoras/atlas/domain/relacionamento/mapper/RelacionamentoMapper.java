package br.com.copadasautoras.atlas.domain.relacionamento.mapper;

import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.AtualizarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.request.CriarRelacionamentoRequest;
import br.com.copadasautoras.atlas.domain.relacionamento.dto.response.RelacionamentoResponse;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;

public final class RelacionamentoMapper {

    private RelacionamentoMapper() {
    }

    public static Relacionamento toEntity(CriarRelacionamentoRequest request,
                                          Projeto projeto,
                                          Organizacao organizacao) {

        Relacionamento relacionamento = new Relacionamento();
        relacionamento.setProjeto(projeto);
        relacionamento.setOrganizacao(organizacao);
        relacionamento.setStatus(request.status());
        relacionamento.setOrigem(request.origem());
        relacionamento.setObjetivo(request.objetivo());
        relacionamento.setPrioridade(
                request.prioridade() != null ? request.prioridade() : PrioridadeRelacionamento.MEDIA
        );
        relacionamento.setResponsavel(request.responsavel());
        relacionamento.setObservacoes(request.observacoes());
        relacionamento.setDataInicio(request.dataInicio());

        return relacionamento;
    }

    public static void atualizar(Relacionamento relacionamento, AtualizarRelacionamentoRequest request) {

        relacionamento.setStatus(request.status());
        relacionamento.setOrigem(request.origem());
        relacionamento.setObjetivo(request.objetivo());

        if (request.prioridade() != null) {
            relacionamento.setPrioridade(request.prioridade());
        }

        relacionamento.setResponsavel(request.responsavel());
        relacionamento.setObservacoes(request.observacoes());
        relacionamento.setDataEncerramento(request.dataEncerramento());
    }

    public static RelacionamentoResponse toResponse(Relacionamento relacionamento) {

        Projeto projeto = relacionamento.getProjeto();
        Organizacao organizacao = relacionamento.getOrganizacao();

        return new RelacionamentoResponse(
                relacionamento.getId(),
                projeto.getId(),
                projeto.getNome(),
                organizacao.getId(),
                organizacao.getNome(),
                relacionamento.getStatus(),
                relacionamento.getOrigem(),
                relacionamento.getObjetivo(),
                relacionamento.getPrioridade(),
                relacionamento.getResponsavel(),
                relacionamento.getObservacoes(),
                relacionamento.getDataInicio(),
                relacionamento.getDataEncerramento(),
                relacionamento.getCreatedAt(),
                relacionamento.getUpdatedAt()
        );
    }

}