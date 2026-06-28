package br.com.copadasautoras.atlas.domain.oportunidade.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusOportunidade;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
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

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "oportunidade")
public class Oportunidade extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relacionamento_id", nullable = false)
    private Relacionamento relacionamento;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoOportunidade tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatusOportunidade status = StatusOportunidade.ABERTA;

    @Column(name = "valor_solicitado", precision = 15, scale = 2)
    private BigDecimal valorSolicitado;

    @Column(name = "valor_aprovado", precision = 15, scale = 2)
    private BigDecimal valorAprovado;

    @Column(nullable = false)
    private Integer probabilidade = 0;

    @Column(name = "data_prevista")
    private LocalDate dataPrevista;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}
