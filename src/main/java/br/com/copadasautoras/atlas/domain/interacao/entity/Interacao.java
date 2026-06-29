package br.com.copadasautoras.atlas.domain.interacao.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.interacao.enums.ResultadoInteracao;
import br.com.copadasautoras.atlas.domain.interacao.enums.TipoInteracao;
import br.com.copadasautoras.atlas.domain.relacionamento.entity.Relacionamento;
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
@Table(name = "interacao")
public class Interacao extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relacionamento_id", nullable = false)
    private Relacionamento relacionamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoInteracao tipo;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ResultadoInteracao resultado;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_interacao", nullable = false)
    private LocalDateTime dataInteracao;

    @Column(name = "proxima_acao", columnDefinition = "TEXT")
    private String proximaAcao;

    @Column(name = "data_proxima_acao")
    private LocalDate dataProximaAcao;

    @Column(name = "realizada_por", length = 150)
    private String realizadaPor;

}