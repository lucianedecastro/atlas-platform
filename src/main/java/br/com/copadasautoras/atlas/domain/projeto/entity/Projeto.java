package br.com.copadasautoras.atlas.domain.projeto.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.projeto.enums.StatusProjeto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Projeto extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatusProjeto status = StatusProjeto.IDEACAO;

    @Column(length = 200)
    private String proponente;

    @Column(name = "lei_incentivo", nullable = false)
    private Boolean leiIncentivo = false;

    @Column(name = "orcamento_previsto", precision = 15, scale = 2)
    private BigDecimal orcamentoPrevisto;

    @Column(name = "valor_captado", precision = 15, scale = 2)
    private BigDecimal valorCaptado = BigDecimal.ZERO;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

}
