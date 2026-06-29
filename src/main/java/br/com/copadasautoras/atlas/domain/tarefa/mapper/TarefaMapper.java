package br.com.copadasautoras.atlas.domain.tarefa.mapper;

import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.AtualizarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.request.CriarTarefaRequest;
import br.com.copadasautoras.atlas.domain.tarefa.dto.response.TarefaResponse;
import br.com.copadasautoras.atlas.domain.tarefa.entity.Tarefa;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;

import java.time.LocalDateTime;

public final class TarefaMapper {

    private TarefaMapper() {
    }

    public static Tarefa toEntity(CriarTarefaRequest request, Oportunidade oportunidade) {

        Tarefa tarefa = new Tarefa();
        tarefa.setOportunidade(oportunidade);
        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setPrioridade(request.prioridade() != null ? request.prioridade() : PrioridadeRelacionamento.MEDIA);
        tarefa.setPrazo(request.prazo());
        tarefa.setResponsavel(request.responsavel());

        return tarefa;
    }

    public static void atualizar(Tarefa tarefa, AtualizarTarefaRequest request) {

        StatusTarefa statusAnterior = tarefa.getStatus();

        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setStatus(request.status());

        if (request.status() == StatusTarefa.CONCLUIDA && statusAnterior != StatusTarefa.CONCLUIDA) {
            tarefa.setConcluidaEm(LocalDateTime.now());
        } else if (request.status() != StatusTarefa.CONCLUIDA) {
            tarefa.setConcluidaEm(null);
        }

        if (request.prioridade() != null) {
            tarefa.setPrioridade(request.prioridade());
        }

        tarefa.setPrazo(request.prazo());
        tarefa.setResponsavel(request.responsavel());
    }

    public static TarefaResponse toResponse(Tarefa tarefa) {

        Oportunidade oportunidade = tarefa.getOportunidade();
        Relacionamento relacionamento = oportunidade.getRelacionamento();

        return new TarefaResponse(
                tarefa.getId(),
                oportunidade.getId(),
                oportunidade.getTitulo(),
                relacionamento.getProjeto().getNome(),
                relacionamento.getOrganizacao().getNome(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getPrioridade(),
                tarefa.getPrazo(),
                tarefa.getConcluidaEm(),
                tarefa.getResponsavel(),
                tarefa.getCreatedAt(),
                tarefa.getUpdatedAt()
        );
    }

}