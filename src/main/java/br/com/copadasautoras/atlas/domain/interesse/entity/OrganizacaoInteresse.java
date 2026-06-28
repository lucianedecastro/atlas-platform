package br.com.copadasautoras.atlas.domain.interesse.entity;

import br.com.copadasautoras.atlas.core.entity.BaseEntity;
import br.com.copadasautoras.atlas.domain.organizacao.entity.Organizacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "organizacao_interesse")
public class OrganizacaoInteresse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id", nullable = false)
    private Organizacao organizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interesse_id", nullable = false)
    private Interesse interesse;

    @Column(nullable = false)
    private Short peso;

    @Column(nullable = false)
    private Short confianca = 100;

    @Column(length = 150)
    private String fonte;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}
