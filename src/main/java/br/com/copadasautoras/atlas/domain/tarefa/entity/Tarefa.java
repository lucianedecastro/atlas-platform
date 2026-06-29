package br.com.copadasautoras.atlas.domain.tarefa.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.oportunidade.entity.Oportunidade;
import br.com.copadasautoras.atlas.domain.relacionamento.enums.PrioridadeRelacionamento;
import br.com.copadasautoras.atlas.domain.tarefa.enums.StatusTarefa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tarefa")
public class Tarefa extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oportunidade_id", nullable = false)
    private Oportunidade oportunidade;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusTarefa status = StatusTarefa.PENDENTE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrioridadeRelacionamento prioridade = PrioridadeRelacionamento.MEDIA;

    @Column
    private LocalDate prazo;

    @Column(name = "concluida_em")
    private LocalDateTime concluidaEm;

    @Column(length = 150)
    private String responsavel;

}
