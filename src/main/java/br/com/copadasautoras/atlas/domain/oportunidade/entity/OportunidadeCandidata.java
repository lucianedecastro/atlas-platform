package br.com.copadasautoras.atlas.domain.oportunidade.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.StatusCandidata;
import br.com.copadasautoras.atlas.domain.oportunidade.enums.TipoOportunidade;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import br.com.copadasautoras.atlas.domain.projeto.entity.Projeto;
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
@Table(name = "oportunidade_candidata")
public class OportunidadeCandidata extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoOportunidade tipo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "valor_estimado", precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @Column(name = "data_limite")
    private LocalDate dataLimite;

    @Column(nullable = false, length = 150)
    private String fonte;

    @Column(name = "url_origem", length = 500)
    private String urlOrigem;

    @Column(nullable = false)
    private Integer confianca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusCandidata status = StatusCandidata.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id")
    private Organizacao organizacao;

    @Column(name = "organizacao_nome_sugerido", length = 150)
    private String organizacaoNomeSugerido;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}
