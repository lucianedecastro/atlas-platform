package br.com.copadasautoras.atlas.domain.organizacao.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.organizacao.enums.TipoOrganizacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "organizacao")
public class Organizacao extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(name = "nome_fantasia", length = 150)
    private String nomeFantasia;

    @Column(length = 18)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoOrganizacao tipo;

    @Column(length = 100)
    private String segmento;

    @Column(length = 255)
    private String site;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "utiliza_lei_incentivo", nullable = false)
    private Boolean utilizaLeiIncentivo = false;

    @Column(name = "possui_instituto", nullable = false)
    private Boolean possuiInstituto = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

}